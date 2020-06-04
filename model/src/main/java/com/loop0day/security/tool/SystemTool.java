/*
 * @author: loop0day
 * @date: 2020/3/14
 * @description: 系统工具类
 */

package com.loop0day.security.tool;

import com.loop0day.security.bean.DataFlow;
import com.loop0day.security.bean.Node;
import com.loop0day.security.conf.Config;
import com.loop0day.security.db.bean.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.loop0day.security.conf.Config.NET_PRIVATE_ADDRESSES;

public class SystemTool {
  /**
   * 判断设备是否是交换机设备
   *
   * @param dev 设备
   * @return 真 表示交换机设备，假 表示非交换机设备
   */
  public static boolean isSwitch(Device dev) {
    return dev.getDeviceName().startsWith(Config.DEVICE_NAME_SWITCH_PREFIX);
  }

  /**
   * 判断设备是否是路由转发设备
   *
   * @param dev 设备
   * @return 真 表示路由转发设备，假 表示非路由转发设备
   */
  public static boolean isRouter(Device dev) {
    return dev.getDeviceType().startsWith(Config.DEVICE_TYPE_NETWORK_PREFIX);
  }

  /**
   * 判断设备是否是安全设备
   *
   * @param dev 设备
   * @return 真 表示安全设备，假 表示非安全设备
   */
  public static boolean isSecurity(Device dev) {
    return dev.getDeviceType().contains(Config.DEVICE_TYPE_SECURITY_STR);
  }

  /**
   * 判断网络地址是否是公网地址
   *
   * @param address 网络地址
   * @param netmask 子网掩码
   * @return 真表示公网地址，假表示私有地址
   */
  public static boolean isPublic(String address, String netmask) {
    /* 子网掩码为空即为公网地址 */
    if (StringUtils.isEmpty(netmask)) return true;
    /* 网络地址为默认网关为非公网地址 */
    if (address.equals(Config.ROUTE_SELF_GATEWAY)) return false;

    /* 获得网段地址 */
    String segment = SystemTool.getSegment(address, netmask);

    /* 依次查询是否有私有网段地址 */
    for (String addr : NET_PRIVATE_ADDRESSES) {
      /* 当前网络地址的网段符合私有网段地址 */
      if (addr.equals(segment)) return false;
    }

    return true;
  }

  /**
   * 根据网络地址和子网掩码得到网段地址
   *
   * @param address 网络地址
   * @param netmask 子网掩码
   * @return 网段地址
   */
  public static String getSegment(String address, String netmask) {
    String[] addr = address.split("\\.");
    String[] mask = netmask.split("\\.");

    /* 如果网络地址和子网掩码长度不一致，则返回空 */
    if (addr.length != mask.length) return null;

    /* 分配网段地址数组空间 */
    String[] seg = new String[addr.length];

    /* 将点分十进制的网络地址和子网掩码取与 */
    for (int i = 0; i < addr.length; ++i) {
      seg[i] = String.valueOf(Integer.parseInt(addr[i]) & Integer.parseInt(mask[i]));
    }

    /* 重新合并成点分十进制的网段地址 */
    return String.join(".", seg);
  }

  /**
   * 判断是否是网段地址
   *
   * @param address 带判断地址
   * @return 真 表示网段地址，假 表示非网段地址
   */
  public static boolean isSegmentAddress(String address) {
    /* 拆分点分十进制 */
    String[] addr = address.split("\\.");

    return Integer.parseInt(addr[addr.length - 1]) == 0;
  }

  /**
   * 判断是否匹配路由网段规则
   *
   * @param d 数据流
   * @param r 路由规则
   * @return 真 表示匹配路由网段规则，假 表示不匹配路由网段规则
   */
  public static boolean isMatchRouteSegmentRule(DataFlow d, DeviceRoute r) {
    /* 数据流目的网段地址 */
    String dSeg = SystemTool.getSegment(d.getDst(), r.getGenmask());
    /* 路由规则网段地址 */
    String rSeg = SystemTool.getSegment(r.getDestination(), r.getGenmask());

    /* 判断是否属于同一网段 */
    if (dSeg != null) return dSeg.equals(rSeg);
    else return false;
  }

  /**
   * 确定最终匹配的路由规则
   *
   * @param staticRule  静态路由规则
   * @param segmentRule 网段路由规则
   * @param defaultRule 默认路由规则
   * @return 匹配的路由规则
   */
  public static DeviceRoute determineMatchedRouteRule(DeviceRoute staticRule, DeviceRoute segmentRule, DeviceRoute defaultRule) {
    if (staticRule != null) return staticRule;
    else if (segmentRule != null) return segmentRule;
    else return defaultRule;
  }

  /**
   * 根据数据流规则设置数据流
   *
   * @param d 数据流对象
   * @param r 数据流规则
   */
  public static void setDataFlowByDataFlowRule(DataFlow d, DeviceDataflow r) {
    /* 赋值源地址 */
    d.setSrc(r.gettSrc().equals(Config.DATAFLOW_NULL_VALUE) ? d.getSrc() : r.gettSrc());
    /* 赋值源端口 */
    d.setSport(r.gettSport().equals(Config.DATAFLOW_NULL_VALUE) ? d.getSport() : r.gettSport());
    /* 赋值目的地址 */
    d.setDst(r.gettDst().equals(Config.DATAFLOW_NULL_VALUE) ? d.getDst() : r.gettDst());
    /* 赋值目的端口 */
    d.setDport(r.gettDport().equals(Config.DATAFLOW_NULL_VALUE) ? d.getDport() : r.gettDport());
    /* 赋值协议 */
    d.setProtocol(r.gettProtocol().equals(Config.DATAFLOW_NULL_VALUE) ? d.getProtocol() : r.gettProtocol());
    /* 赋值加密状态 */
    d.setVariant(r.gettVariantId().equals(Config.DATAFLOW_VARIANT_NULL_VALUE) ? d.getVariant() : r.gettVariantId());
    /* 将非占位符的权限添加到数据流的权限集合中 */
    if (!r.gettPrivilegeId().equals(Config.DATAFLOW_PRIVILEGE_NULL_VALUE))
      d.getPrivileges().add(r.gettPrivilegeId());
  }

  /**
   * 判断当前设备是否是目的设备
   *
   * @param d    数据流
   * @param nets 设备网络地址列表
   * @return 真 表示到达了目的设备，假 表示未到达目的设备
   */
  public static boolean isDataFlowDst(DataFlow d, List<DeviceNetwork> nets) {
    for (DeviceNetwork net : nets) {
      /* 当前设备某网络地址匹配数据流目的地址 */
      if (net.getAddress().equals(d.getDst())) return true;
    }

    return false;
  }

  /**
   * 深拷贝可达路径集合
   *
   * @param paths 可达路径集合
   * @return 拷贝后的可达路径集合
   */
  public static List<List<Node>> deepClonePaths(List<List<Node>> paths) {
    List<List<Node>> ps = new ArrayList<>();

    /* 深拷贝 */
    for (List<Node> path : paths) ps.add(SystemTool.deepClonePath(path));

    return ps;
  }

  /**
   * 深拷贝可达路径
   *
   * @param path 待拷贝可达路径
   * @return 拷贝后的可达路径
   */
  public static List<Node> deepClonePath(List<Node> path) {
    /* 创建新的列表 */
    List<Node> p = new ArrayList<>();

    /* 深拷贝 */
    for (Node n : path) p.add(n.clone());

    return p;
  }

  /**
   * 保留精度
   *
   * @param d 浮点数
   * @return 保留精度后浮点数
   */
  public static Double scaleDouble(Double d) {
    return BigDecimal.valueOf(d).setScale(Config.SERVICE_TESTCASE_RESULT_PRECISION, RoundingMode.HALF_UP).doubleValue();
  }

  /**
   * 根据服务设备对可达路径构造测试用例
   *
   * @param path 可达路径
   * @return 测试用例对象
   */
  public static ServiceTestcase setServiceTestCaseByPath(List<Node> path) {
    /* 获得第一个和最后一个数据流 */
    DataFlow first = path.get(0).getDataflow(), last = path.get(path.size() - 1).getDataflow();
    /* 创建服务设备对测试用例对象 */
    ServiceTestcase st = new ServiceTestcase();

    /* 设置服务设备对测试用例值 */
    st.setSrc(first.getSrc());
    st.setSport(first.getSport());
    st.setDst(first.getDst());
    st.setDport(first.getDport());
    st.setProtocol(first.getProtocol());
    st.setPrivileges(StringUtils.join(last.getPrivileges(), ","));
    st.setVariantId(first.getVariant());

    return st;
  }
  /**
   * 根据服务设备对可达路径构造攻击路径
   *
   * @param path 可达路径
   * @return 测试用例对象
   */
  public static AttackPath setAttackTestCaseBypath(List<Node> path) {
    /* 获得第一个和最后一个数据流 */
    DataFlow first = path.get(0).getDataflow(), last = path.get(path.size() - 1).getDataflow();
    /* 创建服务设备对测试用例对象 */
    AttackPath at = new AttackPath();

    /* 设置服务设备对测试用例值 */
    at.setSrc(first.getSrc());
    at.setSport(first.getSport());
    at.setDst(first.getDst());
    at.setDport(first.getDport());
    at.setProtocol(first.getProtocol());
    at.setPrivilege(StringUtils.join(last.getPrivileges(), ","));

    return at;
  }
  /**
   * 推断最后一个节点对应的威胁编号集合
   *
   * @param node 最后一个节点
   * @param os   是否考察主机安全
   * @return 威胁编号集合
   */
  public static Set<Integer> analyzeThreatId(Node node, boolean os) {
    Integer id = node.getDeviceId();
    DataFlow df = node.getDataflow();
    Set<Integer> lst = new HashSet<>();

    /* 默认威胁 */
    MapTool.selectDeviceThreatDefault().forEach(dt -> lst.add(dt.getThreatId()));

    /* 根据权限查询威胁 */
    MapTool.selectDeviceThreatByDeviceId(id).forEach(dt -> {
      /* 当且仅当主机标志设置才考察主机威胁 */
      if (df.getPrivileges().contains(dt.getPrivilegeId()) && !((SystemTool.isHostThreat(dt.getThreatId()) && !os) || (os && !SystemTool.isHostThreat(dt.getThreatId()))))
        lst.add(dt.getThreatId());
    });

    return lst;
  }

  /**
   * 判断当前威胁是否属于主机层威胁
   *
   * @param threat_id 威胁编号
   * @return 真表示属于主机层威胁，假表示不属于主机层威胁
   */
  public static boolean isHostThreat(Integer threat_id) {
    SecurityThreat st = MapTool.selectSecurityThreatByThreatId(threat_id);
    SecurityFunction sf = MapTool.selectSecurityFunctionByFunctionId(st.getFunctionId());

    return sf.getSecurityClass().equals(Config.HOST_SECURITY_STR);
  }

  /**
   * 判断是否是主机路径
   *
   * @param path 路径
   * @return 真表示主机路径，假表示非主机路径
   */
  public static boolean isHostPath(List<Node> path) {
    /* 多余一个节点肯定不是主机路径 */
    if (path.size() > 1) return false;

    DataFlow d = path.get(0).getDataflow();

    return d.getSrc().equals(d.getDst()) && d.getDport().equals(Config.DEVICE_SERVICE_NULL_PORT);
  }

  /**
   * 判断等价路径是否已经存在
   *
   * @param paths 路径集合
   * @param path  待判断路径
   * @return 真表示存在，假表示不存在
   */
  public static boolean isPathExist(List<List<Node>> paths, List<Node> path) {
    /* 遍历判断所有可达路径 */
    for (List<Node> p : paths) {
      /* 长度不等直接跳过 */
      if (p.size() != path.size()) continue;

      DataFlow lhs = p.get(0).getDataflow();
      DataFlow rhs = path.get(0).getDataflow();

      if (rhs.getPrivileges().containsAll(lhs.getPrivileges()) && rhs.isEqualExceptPrivileges(lhs)) return true;
    }

    return false;
  }

  /**
   * 判断数据流是否符合设备的目的数据流规则
   *
   * @param device_id 目的设备编号
   * @param d         数据流
   * @return 真表示符合，假表示不符合
   */
  public static boolean isMatchDataFlowDstRules(Integer device_id, DataFlow d) {
    for (DeviceDataflow r : MapTool.selectDeviceDataflowByDeviceId(device_id)) {
      /* 判断是否匹配目的规则 */
      if (r.getDst().equals(d.getDst()) && SystemTool.isMatchDataFlowRule(d, r)) return true;
    }
    /* 没有匹配的就返回假 */
    return false;
  }

  /**
   * 判断数据流是否匹配数据流规则
   *
   * @param d 数据流对象
   * @param r 数据流规则
   * @return 真 表示匹配，假 表示不匹配
   */
  public static boolean isMatchDataFlowRule(DataFlow d, DeviceDataflow r) {
    /* 判断数据流规则是否匹配 */
    return (r.getSrc().equals(Config.DATAFLOW_WILDCARD) || r.getSrc().equals(d.getSrc()))
      && (r.getSport().equals(Config.DATAFLOW_WILDCARD) || r.getSport().equals(d.getSport()))
      && (r.getDst().equals(Config.DATAFLOW_WILDCARD) || r.getDst().equals(d.getDst()))
      && (r.getDport().equals(Config.DATAFLOW_WILDCARD) || r.getDport().equals(d.getDport()))
      && (r.getProtocol().equals(Config.DATAFLOW_WILDCARD) || r.getProtocol().toUpperCase().equals(d.getProtocol().toUpperCase()))
      && (r.getVariantId().equals(Config.DATAFLOW_VARIANT_WILDCARD) || r.getVariantId().equals(d.getVariant()))
      && (r.getPrivilegeId().equals(Config.DATAFLOW_PRIVILEGE_WILDCARD) || d.getPrivileges().contains(r.getPrivilegeId()));
  }
}
