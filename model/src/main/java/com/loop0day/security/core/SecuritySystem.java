/*
 * @author: loop0day
 * @date: 2020/3/6
 * @description: 测试评估系统核心主类
 */

package com.loop0day.security.core;

import com.loop0day.security.bean.DataFlow;
import com.loop0day.security.bean.Node;
import com.loop0day.security.conf.Config;
import com.loop0day.security.db.bean.*;
import com.loop0day.security.tool.MapTool;
import com.loop0day.security.tool.SystemTool;
import com.xml.Tool.StandXmlTool;
import com.xml.Tool.Xml_Tool;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.loop0day.security.tool.MapTool.*;


public class SecuritySystem {
  /* 全局路径对象 */
  private final List<Node> path = new ArrayList<>();
  /* 所有可达路径 */
  private final List<List<Node>> paths = new ArrayList<>();
  /* 全局主机标志，全局转发标志，全局接受标志 */
  private boolean os, trans, accept;
  /* 服务设备可达路径 */
  private List<List<Node>> servicesPaths;
  /*攻击路径生成轮数*/
  private int general=0;
  /**
   * 初始化所有可达路径
   */
  private void resetP() {
    this.path.clear();
    this.paths.clear();
  }

  /**
   * 初始化全局标志位
   */
  private void resetF() {
    this.trans = true;
    this.accept = false;
  }

  /**
   * 获得可达路径集合
   *
   * @return 可达路径集合
   */
  public List<List<Node>> getServicesPaths() {
    return this.servicesPaths;
  }

  /**
   * 数据流规则处理器，影响当前数据流
   *
   * @param d  数据流
   * @param id 当前设备
   * @return 真 表示接受或者转发，假 表示拒绝
   */
  private boolean doDataFlow(DataFlow d, Integer id) {
    /* 遍历数据流规则 */
    for (DeviceDataflow r : selectDeviceDataflowByDeviceId(id)) {
      if (SystemTool.isMatchDataFlowRule(d, r)) {
        switch (r.getType()) {
          /* 拒绝规则 */
          case Config.DATAFLOW_DENY_STR:
            return false;
          /* 接受规则 */
          case Config.DATAFLOW_ACCEPT_STR:
            return this.accept = true;
          /* 转发规则 */
          case Config.DATAFLOW_TRANS_STR:
            SystemTool.setDataFlowByDataFlowRule(d, r);
            return this.trans = true;
        }
      }
    }

    return false; /* 默认拒绝 */
  }

  /**
   * 路由规则处理器
   *
   * @param d  数据流
   * @param id 当前设备编号
   * @return 对端设备编号
   */
  private Integer doRoute(DataFlow d, Integer id) {
    /* 静态路由规则，网段路由规则，默认路由规则 */
    DeviceRoute staticRule = null, segmentRule = null, defaultRule = null;

    /* 遍历设备路由规则 */
    for (DeviceRoute r : selectDeviceRouteByDeviceId(id)) {
      /* 静态路由规则 */
      if (r.getDestination().equals(d.getDst()))
        staticRule = r;
        /* 默认路由规则 */
      else if (r.getDestination().equals(Config.ROUTE_DEFAULT_DESTINATION))
        defaultRule = r;
        /* 网段路由规则，首先得是网段地址 */
      else if (SystemTool.isSegmentAddress(r.getDestination()) && SystemTool.isMatchRouteSegmentRule(d, r))
        segmentRule = r;
    }

    DeviceRoute matchedRule = SystemTool.determineMatchedRouteRule(staticRule, segmentRule, defaultRule);

    /* 无匹配路由规则，返回路由错误码 */
    if (matchedRule == null) return Config.ROUTE_ERROR_CODE;

    /* 判断网关是否处于公网 */
    if (SystemTool.isPublic(matchedRule.getGateway(), matchedRule.getGenmask())) {
      /* 返回公网网关所在设备编号 */
      return selectOneDeviceNetworkByAddress(matchedRule.getGateway()).getDeviceId();
    } else {
      /* 返回出口接口对端设备编号 */
      return selectDeviceConnectByDeviceIdAndInterfaceId(id, matchedRule.getInterfaceId()).getPeerDeviceId();
    }
  }

  /**
   * 交换机规则处理器
   *
   * @param d     数据流
   * @param id    设备编号
   * @param preId 前设备编号
   */
  private void doSwitch(DataFlow d, Integer id, Integer preId) {
    /* 遍历所有与交换机相连的设备 */
    for (DeviceConnect dc : selectDeviceConnectByDeviceId(id)) {
      /* 不要重复判断来源端口设备 */
      if (!dc.getPeerDeviceId().equals(preId)) {
        /* 传递给对端设备 */
        analyzeP(d, dc.getPeerDeviceId(), id);
      }
    }
  }

  /**
   * 处理路由转发
   *
   * @param d     数据流
   * @param id    当前设备编号
   * @param preId 前设备编号
   * @return 真 表示可路由，假 表示不可路由
   */
  private boolean doRouter(DataFlow d, Integer id, Integer preId) {
    /* 全局转发标志位设为假 */
    this.trans = false;

    /* 处理路由规则，并返回对端设备编号 */
    Integer peerId = this.doRoute(d, id);

    /* 如果未找到路由规则 */
    if (peerId.equals(Config.ROUTE_ERROR_CODE)) {
      /* 接受标志位恢复 */
      this.accept = false;
      return false;
    }

    /* 如果符合接受数据流规则 */
    if (this.accept && peerId.equals(preId)) {
      /* 接受标志位恢复 */
      this.accept = false;
      return false;
    }

    /* 接受标志位恢复 */
    this.accept = false;

    /* 将数据包传递给对端 */
    analyzeP(d, peerId, id);

    return true;
  }

  /**
   * 旁路部署处理器
   *
   * @param path    可达路径
   * @param cur     当前索引
   * @param tc      响应结果
   * @param t       威胁类型
   * @param exclude 安全设备排除列表
   */
  private void doDeploy(List<Node> path, int cur, ServiceTestcase tc, Integer t, List<Integer> exclude) {
    /* 获取当前设备编号，上个设备编号，下个设备编号 */
    Integer id = path.get(cur).getDeviceId();
    Integer preId = cur > 1 ? path.get(cur - 1).getDeviceId() : -1;
    Integer nextId = cur < path.size() - 1 ? path.get(cur + 1).getDeviceId() : -1;
    /* 初始检测值和记录值 */
    double record = 0.0, detect = 0.0;

    for (DeviceConnect dc : selectDeviceConnectByDeviceId(id)) {
      Device dev = selectDeviceByDeviceId(dc.getPeerDeviceId());

      if (!SystemTool.isSecurity(dev) || exclude.contains(dev.getDeviceId()) || dev.getDeviceId().equals(preId) || dev.getDeviceId().equals(nextId) || (dev.getDeviceDeploy() == 0))
        continue;

      /* 根据安全设备编号和威胁编号获取安全设备单机测试结果 */
      SingleTest st = selectSingleTestByDeviceIdAndThreatId(dev.getDeviceId(), t, path.get(cur).getDataflow().getVariant());

      /* 单机测试结果不为空 */
      if (st == null) continue;

      if (record == Config.SINGLE_TEST_UPPER_LIMIT || st.getRecord().equals(Config.SINGLE_TEST_UPPER_LIMIT))
        record = Config.SINGLE_TEST_UPPER_LIMIT;
      else record = (record != 0.0 && st.getRecord() != 0.0) ? (record + st.getRecord()) / 2 : st.getRecord();

      if (detect == Config.SINGLE_TEST_UPPER_LIMIT || st.getDetect().equals(Config.SINGLE_TEST_UPPER_LIMIT))
        detect = Config.SINGLE_TEST_UPPER_LIMIT;
      else detect = (detect != 0.0 && st.getDetect() != 0.0) ? (detect + st.getDetect()) / 2 : st.getDetect();
    }

    /* 设置记录率 */
    tc.setRecord(SystemTool.scaleDouble(tc.getRecord() + (1 - tc.getRecord()) * record));
    if (tc.getRecord() > Config.SINGLE_TEST_UPPER_LIMIT)
      tc.setRecord(SystemTool.scaleDouble(Config.SINGLE_TEST_UPPER_LIMIT));

    /* 设置检测率 */
    tc.setDetect(SystemTool.scaleDouble(tc.getDetect() + (1 - tc.getDetect()) * detect));
    if (tc.getDetect() > Config.SINGLE_TEST_UPPER_LIMIT)
      tc.setDetect(SystemTool.scaleDouble(Config.SINGLE_TEST_UPPER_LIMIT));
  }

  /**
   * 主分析器
   *
   * @param d     数据流
   * @param id    设备编号
   * @param preId 前设备编号
   */
  private void analyze(DataFlow d, Integer id, Integer preId) {
    /* 创建当前节点对象 */
    Node n = new Node(id, d.clone());
    /* 获取设备信息 */
    Device dev = selectDeviceByDeviceId(id);

    /* 将当前设备添加到可达路径中 */
    this.path.add(n);

    /* 交换机设备 */
    if (SystemTool.isSwitch(dev)) doSwitch(d, id, preId);
    else { /* 非交换机设备 */
      /* 处理数据流规则，数据流直接被影响，如果被拒绝则回溯 */
      if (!this.doDataFlow(d, id)) {
        this.path.remove(n);
        return;
      }

      /* 判断是否是目的设备，如果是目的设备则考察是否满足数据流的目的地址规则 */
      if (SystemTool.isDataFlowDst(d, selectDeviceNetworkByDeviceId(id)) && SystemTool.isMatchDataFlowDstRules(id, d)) {
        /* 全局接受标记恢复 */
        this.accept = false;
        /* 如果等价路径不存在，深度拷贝并存储路径 */
        if (!SystemTool.isPathExist(this.paths, this.path)) this.paths.add(SystemTool.deepClonePath(this.path));
      } else if (this.trans || SystemTool.isRouter(dev)) { /* 判断是否是路由转发设备 */
        if (!this.doRouter(d, id, preId)) {
          this.path.remove(n);
          return;
        }
      }
    }
    /* 回溯 */
    this.path.remove(n);
  }

  /**
   * 主分析器包装方法
   *
   * @param d     数据流
   * @param id    设备编号
   * @param preId 前设备编号
   */
  private void analyzeP(DataFlow d, Integer id, Integer preId) {
    /* 如果是第一个节点，重置全局标志位 */
    if (this.path.size() == 0) this.resetF();

    /* 执行一遍空权限 */
    analyze(d.clone(), id, preId);

    /* 如果回到初始节点则不重新赋予权限 */
    if (this.path.size() > 0 && this.path.get(0).getDeviceId().equals(id)) return;

    /* 遍历用户权限集合 */
    for (UserPrivilegeKey up : selectUserPrivilegeByDeviceId(id)) {
      /* 如果是第一个节点，重置全局标志位 */
      if (this.path.size() == 0) this.resetF();

      /* 深拷贝数据流 */
      DataFlow df = d.clone();

      /* 添加权限 */
      df.getPrivileges().add(up.getPrivilegeId());
      /* 核心递归函数 */
      analyze(df.clone(), id, preId);
    }
  }

  /**
   * 获得所有源设备集合和目的设备集合之间的数据流
   *
   * @param srcs 源设备集合
   * @param dsts 目的设备集合
   * @return 源设备集合和目的设备集合之间的数据流
   */
  private List<List<Node>> calcPaths(List<Device> srcs, List<Device> dsts) {
    /* 重置所有可达路径 */
    this.resetP();

    /* 遍历源设备 */
    for (Device src : srcs) {
      /* 遍历源设备网络信息列表 */
      for (DeviceNetwork snet : selectDeviceNetworkByDeviceId(src.getDeviceId())) {
        /* 遍历目的设备 */
        for (Device dst : dsts) {
          /* 遍历目的设备开放的服务 */
          for (DeviceService dsrv : selectDeviceServiceByDeviceId(dst.getDeviceId())) {
            /* 初始化主机安全标志 */
            this.os = dsrv.getPort().equals(Config.DEVICE_SERVICE_NULL_PORT) && src.equals(dst);
            /* 非主机安全标志，则跳过空端口服务 */
            if (!this.os && dsrv.getPort().equals(Config.DEVICE_SERVICE_NULL_PORT)) continue;

            /* 遍历目的设备网络地址 */
            for (DeviceNetwork dnet : selectDeviceNetworkByDeviceId(dst.getDeviceId())) {
              /* 构造数据流 */
              DataFlow d = new DataFlow(snet.getAddress(), this.os ? Config.DEVICE_SERVICE_NULL_PORT : Config.DATAFLOW_DEFAULT_SRC_PORT, dnet.getAddress(), dsrv.getPort(), dsrv.getProtocol(), new HashSet<>(), Config.DATAFLOW_DEFAULT_VARIANT);

              this.analyzeP(d, src.getDeviceId(), -1);
            }
          }
        }
      }
    }

    return SystemTool.deepClonePaths(this.paths);
  }

  /**
   * 获得所有服务设备对数据流
   */
  public void calcServicePaths() {
    List<Device> srcs = new ArrayList<>();
    List<Device> dsts = new ArrayList<>();

    srcs.addAll(selectServiceDevice());
    dsts.addAll(selectServiceDevice());
    dsts.addAll(selectNetworkDevice());

    this.servicesPaths = this.calcPaths(srcs, dsts);
  }

  /**
   * 生成服务设备对之间数据流的测试用例
   */
  public void makeServiceTestCase() {
    /* 获得所有设备对之间可达路径 */
    if (this.servicesPaths == null) this.calcServicePaths();

    /* 遍历每条可达路径 */
    for (List<Node> path : this.servicesPaths) {
      /* 根据可达路径构造服务设备对测试用例 */
      ServiceTestcase st = SystemTool.setServiceTestCaseByPath(path);

      for (Integer t : SystemTool.analyzeThreatId(path.get(path.size() - 1), SystemTool.isHostPath(path))) {
        /* 设置威胁编号 */
        st.setThreatId(t);

        /* 插入服务设备对测试用例 */
        if (!insertServiceTestcaseSelective(st)) return;
      }
    }

    /* 提交插入的数据 */
    commit();
  }
  /**
   * 生成攻击图的所有可达路径
   */
  public void   makeAttackTestCase() {
    /* 获得所有业务设备对之间可达路径 */
    this.calcServicePaths();
    /* 遍历每条可达路径 */
    for (List<Node> path : this.servicesPaths) {
      /* 根据可达路径构造攻击路径用例 */
      AttackPath at = SystemTool.setAttackTestCaseBypath(path);
      DeviceNetwork id = SelectIdByIp(at.getDst()).get(0);
      for (DeviceThreat t : MapTool.selectDeviceThreatByDeviceId(id.getDeviceId())) {
        //判断存在此漏洞且存在可利用模板
        List<AttackModel> am = selectModelByVulnID(t.getThreatId());
        if (am.size()>0&&selectPathPrivilegeExists(at,am.get(0).getPrePrivilegeId()))
        {
          /* 根据权限设置漏洞编号 */
          at.setVulnId(t.getThreatId());
          /* 先判断是否存在，然后插入攻击路径 */
          if (!selectAttackPathExists(at))
            insertAttackPathSelective(at);
        }
      }
    }
    commit();
  }

  private boolean selectPathPrivilegeExists(AttackPath at, Integer prePrivilegeId) {
    String[] path_P=at.getPrivilege().split(",");
    for (int i=0;i<path_P.length;i++)
    {
      if (Integer.parseInt(path_P[i])==prePrivilegeId)
        return true;
    }
    return false;
  }

  /**
   * 生成攻击路径主函数
   */
  public void buildAttackPath() throws FileNotFoundException {
    //生成可达路径
    makeAttackTestCase();
  }

  /**
   * 初始化导入数据
   */
  private void initData() throws FileNotFoundException {
    ScriptRunner scriptRunner = new ScriptRunner(MapTool.session.getConnection());
    Resources.setCharset(StandardCharsets.UTF_8);
    scriptRunner.runScript(new FileReader(com.xml.Conf.Config.Init_sql));
  }

  /**
   * 匹配攻击模板
   *
   */
  public void matchAttackModel(){
    System.out.println("第"+this.general+"轮攻击路径生成");
    for (AttackPath path : selectAllattackPaths())
      /*查询所有攻击路径*/
    {
      /*根据漏洞ID查可以利用攻击模板*/
      if (!selectModelByVulnID(path.getVulnId()).isEmpty())
      {
        /*利用漏洞提升权限*/
        int UP_p = selectModelByVulnID(path.getVulnId()).get(0).getFinPrivilegeId();
        /* 写入获取的权限，并将利用过的漏洞flag置为1*/
        if (!selectUserPrivilegeExists(UP_p)) {
          RiseUserPrivilege(UP_p);
          UpdateVulnFlag(path.getVulnId());
        }
      }
    }
//    searchExp();
  }
  /**
   * 推导响应结果
   *
   * @param exclude 待排除安全设备列表
   */
  public void analyzeServiceResult(List<Integer> exclude) {
    /* 获得所有设备对之间可达路径 */
    if (this.servicesPaths == null) this.calcServicePaths();

    /* 遍历每条可达路径 */
    for (List<Node> path : this.servicesPaths) {
      /* 遍历每种威胁 */
      for (Integer t : SystemTool.analyzeThreatId(path.get(path.size() - 1), SystemTool.isHostPath(path))) {
        /* 初始化测试用例对象 */
        ServiceTestcase tc = new ServiceTestcase(null, null, null, null, null, null, null, null, null, 0.0, 0.0, 0.0, 0.0);

        /* 遍历每个节点 */
        for (int i = 0; i < path.size(); ++i) {
          /* 获取当前节点 */
          Node node = path.get(i);
          /* 获取设备编号对应的设备 */
          Device dev = selectDeviceByDeviceId(node.getDeviceId());
          /* 处理旁路部署设备 */
          if (SystemTool.isRouter(dev)) {
            this.doDeploy(path, i, tc, t, exclude);
          }

          /*  获取威胁对应的功能编号 */
          Integer function_id = selectSecurityThreatByThreatId(t).getFunctionId();
          /* 根据设备编号和功能编号查询设备安全信息 */
          List<DeviceSecurityKey> dss = selectDeviceSecurityByDeviceIdAndFunctionId(dev.getDeviceId(), function_id);

          if (dss == null || dss.isEmpty()) continue; /* 该节点无安全功能则跳过 */

          /* 遍历多个同一功能提供者 */
          for (DeviceSecurityKey ds : dss) {
            /* 跳过非安全设备和排除的安全设备 */
            if (!exclude.contains(ds.getOwnerId())) {
              /* 根据安全设备编号和威胁编号获取安全设备单机测试结果 */
              SingleTest st = selectSingleTestByDeviceIdAndThreatId(ds.getOwnerId(), t, node.getDataflow().getVariant());

              /* 如果安全设备不能防御该种威胁，则跳过 */
              if (st == null) continue;

              /* 设置记录率 */
              tc.setRecord(SystemTool.scaleDouble(tc.getRecord() + (1 - tc.getRecord()) * st.getRecord()));
              if (tc.getRecord() > Config.SINGLE_TEST_UPPER_LIMIT)
                tc.setRecord(SystemTool.scaleDouble(Config.SINGLE_TEST_UPPER_LIMIT));

              /* 设置检测率 */
              tc.setDetect(SystemTool.scaleDouble(tc.getDetect() + (1 - tc.getDetect()) * st.getDetect()));
              if (tc.getDetect() > Config.SINGLE_TEST_UPPER_LIMIT)
                tc.setDetect(SystemTool.scaleDouble(Config.SINGLE_TEST_UPPER_LIMIT));

              /* 设置告警率 */
              tc.setAlarm(SystemTool.scaleDouble(tc.getAlarm() + (1 - tc.getAlarm()) * st.getAlarm()));
              if (tc.getAlarm() > Config.SINGLE_TEST_UPPER_LIMIT)
                tc.setAlarm(SystemTool.scaleDouble(Config.SINGLE_TEST_UPPER_LIMIT));

              /* 设置阻断率 */
              tc.setBlock(SystemTool.scaleDouble(tc.getBlock() + (1 - tc.getBlock()) * st.getBlock()));
              if (tc.getBlock() > Config.SINGLE_TEST_UPPER_LIMIT)
                tc.setBlock(SystemTool.scaleDouble(Config.SINGLE_TEST_UPPER_LIMIT));
            }
          }
        }

        /* 更新响应结果 */
        updateServiceTestCaseSelectiveByPathAndThreatId(tc, path, t);
      }
    }

    /* 提交更新 */
    commit();
  }

  /**
   * 解析XML格式的数据，并将解析后的格式化数据插入数据库中
   *
   * @throws Exception 若有异常，直接抛出
   */
  public void importXMLData() throws Exception {
    //将draw.io导出的xml转成标准格式的xml
    StandXmlTool drawio = new StandXmlTool();
    drawio.CreateXml();
    drawio.BornXml();
    //将标准格式的xml导入数据库，此模块与测试工具系统标准接口对接
    Xml_Tool.XmlWrite();
  }
}
