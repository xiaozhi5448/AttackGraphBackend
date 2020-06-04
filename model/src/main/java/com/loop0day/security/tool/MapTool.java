/*
 * @author: loop0day
 * @date: 2020/3/13
 * @description: 数据库映射工具类
 */

package com.loop0day.security.tool;

import com.loop0day.security.bean.DataFlow;
import com.loop0day.security.bean.Node;
import com.loop0day.security.conf.Config;
import com.loop0day.security.db.bean.*;
import com.loop0day.security.db.dao.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapTool {
  /* 数据库会话对象 */
  public static SqlSession session = null;
  /* 设备基础信息映射对象 */
  private static DeviceMapper deviceMapper;
  /* 设备连接关系映射对象 */
  private static DeviceConnectMapper deviceConnectMapper;
  /* 设备网络信息映射对象 */
  private static DeviceNetworkMapper deviceNetworkMapper;
  /* 设备路由规则映射对象 */
  private static DeviceRouteMapper deviceRouteMapper;
  /* 设备数据流规则映射对象 */
  private static DeviceDataflowMapper deviceDataflowMapper;
  /* 设备服务信息映射对象 */
  private static DeviceServiceMapper deviceServiceMapper;
  /* 设备权限信息映射对象 */
  private static DevicePrivilegeMapper devicePrivilegeMapper;
  /* 用户权限集合映射对象 */
  private static UserPrivilegeMapper userPrivilegeMapper;
  /* 安全功能映射对象 */
  private static SecurityFunctionMapper securityFunctionMapper;
  /* 安全威胁映射对象 */
  private static SecurityThreatMapper securityThreatMapper;
  /* 安全设备安全功能映射对象 */
  private static DeviceSecurityMapper deviceSecurityMapper;
  /* 业务设备威胁映射对象 */
  private static DeviceThreatMapper deviceThreatMapper;
  /* 业务设备对测试用例映射对象 */
  private static ServiceTestcaseMapper serviceTestcaseMapper;
  /* 单机测试映射对象 */
  private static SingleTestMapper singleTestMapper;
  /*攻击模板对象*/
  private static AttackModelMapper attackModelMapper;
  /*攻击路径对象*/
  private static AttackPathMapper attackPathMapper;
  static {
    try {
      /* 数据库会话工厂对象 */
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(Config.MYBATIS_CONF_PATH));
      /* 初始化数据库会话对象 */
      MapTool.session = sqlSessionFactory.openSession();
    } catch (IOException e) {
      e.printStackTrace();
    }

    /* 断言数据库会话对象不为空 */
    assert session != null;

    /* 初始化设备基础信息对象 */
    MapTool.deviceMapper = session.getMapper(DeviceMapper.class);
    /* 初始化设备连接关系对象 */
    MapTool.deviceConnectMapper = session.getMapper(DeviceConnectMapper.class);
    /* 初始化网络信息映射对象 */
    MapTool.deviceNetworkMapper = session.getMapper(DeviceNetworkMapper.class);
    /* 初始化设备路由规则映射对象 */
    MapTool.deviceRouteMapper = session.getMapper(DeviceRouteMapper.class);
    /* 初始化设备数据流规则映射对象 */
    MapTool.deviceDataflowMapper = session.getMapper(DeviceDataflowMapper.class);
    /* 初始化设备服务信息映射对象 */
    MapTool.deviceServiceMapper = session.getMapper(DeviceServiceMapper.class);
    /* 初始化设备权限信息映射对象 */
    MapTool.devicePrivilegeMapper = session.getMapper(DevicePrivilegeMapper.class);
    /* 初始化用户权限映射对象 */
    MapTool.userPrivilegeMapper = session.getMapper(UserPrivilegeMapper.class);
    /* 初始化安全功能映射对象 */
    MapTool.securityFunctionMapper = session.getMapper(SecurityFunctionMapper.class);
    /* 初始化安全威胁映射对象 */
    MapTool.securityThreatMapper = session.getMapper(SecurityThreatMapper.class);
    /* 初始化安全设备安全功能映射对象 */
    MapTool.deviceSecurityMapper = session.getMapper(DeviceSecurityMapper.class);
    /* 初始化业务设备威胁映射对象 */
    MapTool.deviceThreatMapper = session.getMapper(DeviceThreatMapper.class);
    /* 初始化业务设备对测试用例映射对象 */
    MapTool.serviceTestcaseMapper = session.getMapper(ServiceTestcaseMapper.class);
    /* 初始化单机测试映射对象 */
    MapTool.singleTestMapper = session.getMapper(SingleTestMapper.class);
    /* 攻击模板对象*/
    MapTool.attackModelMapper = session.getMapper(AttackModelMapper.class);
    /* 攻击路径对象*/
    MapTool.attackPathMapper = session.getMapper(AttackPathMapper.class);
  }

  /**
   * 修改数据库后提交
   */
  public static void commit() {
    MapTool.session.commit();
  }

  /**
   * 关闭数据库会话
   */
  public static void close() {
    MapTool.session.close();
  }

  /**
   * 查询所有设备
   *
   * @return 所有设备对象列表
   */
  public static List<Device> selectAllDevice() {
    return MapTool.deviceMapper.selectByExample(new DeviceExample());
  }

  /**
   * 查询所有业务设备
   *
   * @return 所有业务设备列表
   */
  public static List<Device> selectServiceDevice() {
    DeviceExample example = new DeviceExample();
    DeviceExample.Criteria criteria = example.createCriteria();

    /* 查询业务设备名称前缀开头 */
    criteria.andDeviceTypeLike(Config.DEVICE_TYPE_SERVICE_PREFIX + '%');

    return MapTool.deviceMapper.selectByExample(example);
  }

  /**
   * 查询所有网络设备
   *
   * @return 所有网络设备列表
   */
  public static List<Device> selectNetworkDevice() {
    DeviceExample example = new DeviceExample();
    DeviceExample.Criteria criteria = example.createCriteria();

    /* 查询以网络设备名称前缀开头 */
    criteria.andDeviceTypeLike(Config.DEVICE_TYPE_NETWORK_PREFIX + '%');

    return MapTool.deviceMapper.selectByExample(example);
  }

  /**
   * 查询所有有某安全功能的安全设备
   *
   * @param id 安全功能编号
   * @return 所有有该安全功能的安全设备列表
   */
  public static List<Device> selectSecurityDeviceByFunctionId(Integer id) {
    List<Device> lst = new ArrayList<>();

    MapTool.selectSecurityDevice().forEach(dev -> {
      for (DeviceSecurityKey ds : MapTool.selectDeviceSecurityByDeviceId(dev.getDeviceId())) {
        if (ds.getFunctionId().equals(id)) {
          lst.add(dev);
          break;
        }
      }
    });

    return lst;
  }

  /**
   * 查询所有安全设备
   *
   * @return 所有安全设备列表
   */
  public static List<Device> selectSecurityDevice() {
    DeviceExample example = new DeviceExample();
    DeviceExample.Criteria criteria = example.createCriteria();

    /* 查询所有安全类型设备 */
    criteria.andDeviceTypeLike('%' + Config.DEVICE_TYPE_SECURITY_STR + '%');

    return MapTool.deviceMapper.selectByExample(example);
  }

  /**
   * 根据设备编号查询安全设备安全功能对象集合
   *
   * @param id 设备编号
   * @return 安全设备安全功能对象集合
   */
  public static List<DeviceSecurityKey> selectDeviceSecurityByDeviceId(Integer id) {
    DeviceSecurityExample example = new DeviceSecurityExample();
    DeviceSecurityExample.Criteria criteria = example.createCriteria();

    /* 设备编号不为空 */
    if (id != null) {
      criteria.andDeviceIdEqualTo(id);
    } else return null;

    return MapTool.deviceSecurityMapper.selectByExample(example);
  }

  /**
   * 根据设备编号查询设备基础信息
   *
   * @param id 设备编号
   * @return 设备基础信息
   */
  public static Device selectDeviceByDeviceId(Integer id) {
    return MapTool.deviceMapper.selectByPrimaryKey(id);
  }

  /**
   * 根据设备编号查询设备连接关系
   *
   * @param id 设备编号
   * @return 设备连接关系列表
   */
  public static List<DeviceConnect> selectDeviceConnectByDeviceId(Integer id) {
    DeviceConnectExample example = new DeviceConnectExample();
    DeviceConnectExample.Criteria criteria = example.createCriteria();

    /* 设备编号不为空 */
    if (id != null) {
      criteria.andDeviceIdEqualTo(id);
    } else return null;

    return MapTool.deviceConnectMapper.selectByExample(example);
  }

  /**
   * 根据设备编号和接口编号查询设备连接关系
   *
   * @param deviceId    设备编号
   * @param interfaceId 接口编号
   * @return 设备连接关系
   */
  public static DeviceConnect selectDeviceConnectByDeviceIdAndInterfaceId(Integer deviceId, Integer interfaceId) {
    /* 构造主键 */
    DeviceConnectKey key = new DeviceConnectKey(deviceId, interfaceId);

    return MapTool.deviceConnectMapper.selectByPrimaryKey(key);
  }

  /**
   * 根据网络地址查询设备网络信息
   *
   * @param addr 网络地址
   * @return 设备网络信息
   */
  public static DeviceNetwork selectOneDeviceNetworkByAddress(String addr) {
    DeviceNetworkExample example = new DeviceNetworkExample();
    DeviceNetworkExample.Criteria criteria = example.createCriteria();

    /* 网络地址不为空 */
    if (StringUtils.isNotEmpty(addr) && !addr.equals(Config.NET_NULL_ADDRESS)) {
      criteria.andAddressEqualTo(addr);
    } else return null;

    /* 只返回查询到的第一条数据 */
    return MapTool.deviceNetworkMapper.selectByExample(example).get(0);
  }

  /**
   * 根据设备编号查询设备网络信息
   *
   * @param id 设备编号
   * @return 设备网络信息列表
   */
  public static List<DeviceNetwork> selectDeviceNetworkByDeviceId(Integer id) {
    DeviceNetworkExample example = new DeviceNetworkExample();
    DeviceNetworkExample.Criteria criteria = example.createCriteria();

    /* 设备编号不为空 */
    if (id != null) {
      criteria.andDeviceIdEqualTo(id);
      /* 地址不为空 */
      criteria.andAddressNotEqualTo(Config.NET_NULL_ADDRESS);
    } else return null;

    return MapTool.deviceNetworkMapper.selectByExample(example);
  }

  /**
   * 根据设备编号查询设备路由规则
   *
   * @param id 设备编号
   * @return 设备路由规则列表
   */
  public static List<DeviceRoute> selectDeviceRouteByDeviceId(Integer id) {
    DeviceRouteExample example = new DeviceRouteExample();
    DeviceRouteExample.Criteria criteria = example.createCriteria();

    /* 设备编号不为空 */
    if (id != null) {
      criteria.andDeviceIdEqualTo(id);
    } else return null;

    return MapTool.deviceRouteMapper.selectByExample(example);
  }

  /**
   * 根据设备编号查询设备数据流规则
   *
   * @param id 设备编号
   * @return 设备数据流规则
   */
  public static List<DeviceDataflow> selectDeviceDataflowByDeviceId(Integer id) {
    DeviceDataflowExample example = new DeviceDataflowExample();
    DeviceDataflowExample.Criteria criteria = example.createCriteria();

    /* 按照优先级排序 */
    example.setOrderByClause("priority asc");

    /* 设备编号不为空 */
    if (id != null) {
      criteria.andDeviceIdEqualTo(id);
    } else return null;

    return MapTool.deviceDataflowMapper.selectByExample(example);
  }

  /**
   * 根据设备编号查询设备开放服务信息
   *
   * @param id 设备编号
   * @return 设备开放服务信息
   */
  public static List<DeviceService> selectDeviceServiceByDeviceId(Integer id) {
    DeviceServiceExample example = new DeviceServiceExample();
    DeviceServiceExample.Criteria criteria = example.createCriteria();

    /* 设备编号不为空 */
    if (id != null) {
      criteria.andDeviceIdEqualTo(id);
    } else return null;

    return MapTool.deviceServiceMapper.selectByExample(example);
  }

  /**
   * 根据设备编号查询用户在当前设备所拥有的权限
   *
   * @param id 设备编号
   * @return 权限李彪
   */
  public static List<UserPrivilegeKey> selectUserPrivilegeByDeviceId(Integer id) {
    UserPrivilegeExample example = new UserPrivilegeExample();
    UserPrivilegeExample.Criteria criteria = example.createCriteria();

    /* 设备编号不为空 */
    if (id != null) {
      criteria.andDeviceIdEqualTo(id);
    } else return null;

    return MapTool.userPrivilegeMapper.selectByExample(example);
  }

  /**
   * 根据安全维度查询安全功能
   *
   * @param dimension 安全维度
   * @return 安全功能对象列表
   */
  public static List<SecurityFunction> selectSecurityFunctionBySecurityClass(String dimension) {
    SecurityFunctionExample example = new SecurityFunctionExample();
    SecurityFunctionExample.Criteria criteria = example.createCriteria();

    /* 维度不为空 */
    if (StringUtils.isNotEmpty(dimension)) {
      criteria.andSecurityClassEqualTo(dimension);
    } else return null;

    return MapTool.securityFunctionMapper.selectByExample(example);
  }

  /**
   * 根据功能编号查询安全功能对象
   *
   * @param function_id 功能编号
   * @return 安全功能对象
   */
  public static SecurityFunction selectSecurityFunctionByFunctionId(Integer function_id) {
    return MapTool.securityFunctionMapper.selectByPrimaryKey(function_id);
  }

  /**
   * 根据设备编号和功能编号查询设备安全信息
   *
   * @param device_id   设备编号
   * @param function_id 功能编号
   * @return 设备安全信息
   */
  public static List<DeviceSecurityKey> selectDeviceSecurityByDeviceIdAndFunctionId(Integer device_id, Integer function_id) {
    DeviceSecurityExample example = new DeviceSecurityExample();
    DeviceSecurityExample.Criteria criteria = example.createCriteria();

    /* 编号都不为空 */
    if (device_id != null && function_id != null) {
      criteria.andDeviceIdEqualTo(device_id);
      criteria.andFunctionIdEqualTo(function_id);
    } else return null;

    return MapTool.deviceSecurityMapper.selectByExample(example);
  }

  /**
   * 查询所有安全功能
   *
   * @return 所有安全功能列表
   */
  public static List<SecurityFunction> selectAllSecurityFunction() {
    return MapTool.securityFunctionMapper.selectByExample(new SecurityFunctionExample());
  }

  /**
   * 根据安全功能编号查询安全威胁
   *
   * @param id 安全功能编号
   * @return 安全威胁列表
   */
  public static List<SecurityThreat> selectSecurityThreatByFunctionId(Integer id) {
    SecurityThreatExample example = new SecurityThreatExample();
    SecurityThreatExample.Criteria criteria = example.createCriteria();

    /* 安全功能编号不为空 */
    if (id != null) {
      criteria.andFunctionIdEqualTo(id);
    } else return null;

    return MapTool.securityThreatMapper.selectByExample(example);
  }

  /**
   * 根据威胁编号查询安全威胁对象
   *
   * @param threat_id 威胁编号
   * @return 安全威胁对象
   */
  public static SecurityThreat selectSecurityThreatByThreatId(Integer threat_id) {
    return MapTool.securityThreatMapper.selectByPrimaryKey(threat_id);
  }

  /**
   * 查询默认威胁
   *
   * @return 默认威胁对象集合
   */
  public static List<DeviceThreat> selectDeviceThreatDefault() {
    DeviceThreatExample example = new DeviceThreatExample();
    DeviceThreatExample.Criteria criteria = example.createCriteria();

    /* 通配的设备编号，服务编号，权限编号 */
    criteria.andDeviceIdEqualTo(Config.DEVICE_THREAT_DEVICE_WILDCARD);
    criteria.andServiceIdEqualTo(Config.DEVICE_THREAT_SERVICE_WILDCARD);
    criteria.andPrivilegeIdEqualTo(Config.DEVICE_THREAT_PRIVILEGE_WILDCARD);

    return MapTool.deviceThreatMapper.selectByExample(example);
  }

  /**
   * 根据设备编号查询设备威胁对象集合
   *
   * @param id 设备编号
   * @return 设备威胁对象集合
   */
  public static List<DeviceThreat> selectDeviceThreatByDeviceId(Integer id) {
    DeviceThreatExample example = new DeviceThreatExample();
    DeviceThreatExample.Criteria criteria = example.createCriteria();

    /* 编号不为空 */
    if (id != null) criteria.andDeviceIdEqualTo(id);
    else return null;

    return MapTool.deviceThreatMapper.selectByExample(example);
  }

  /**
   * 插入业务设备对测试用例
   *
   * @param tc 业务设备对测试用例对象
   * @return 真 表示插入成功，假 表示插入失败
   */
  public static boolean insertServiceTestcaseSelective(ServiceTestcase tc) {
    return MapTool.serviceTestcaseMapper.insertSelective(tc) == 1;
  }
  /**
   * 判断攻击路径是否存在
   *
   * @return*/
  public static boolean selectAttackPathExists(AttackPath tc) {
     AttackPathExample example = new AttackPathExample();
     AttackPathExample.Criteria criteria = example.createCriteria();
     criteria.andSrcEqualTo(tc.getSrc());
     criteria.andSportEqualTo(tc.getSport());
     criteria.andDstEqualTo(tc.getDst());
     criteria.andDportEqualTo(tc.getDport());
     criteria.andProtocolEqualTo(tc.getProtocol());
     criteria.andPrivilegeEqualTo(tc.getPrivilege());
     criteria.andVulnIdEqualTo(tc.getVulnId());
     //如果存在则返回true，不存在则返回false
     if (MapTool.attackPathMapper.selectByExample(example).size()>0)
       return  true;
     else
       return  false;
  }
  /**
   * 插入攻击路径
   *
   * @param tc 业务设备对测试用例对象
   * @return 真 表示插入成功，假 表示插入失败
   */
  public static boolean insertAttackPathSelective(AttackPath tc) {
    return MapTool.attackPathMapper.insert(tc) == 1;
  }
  /**
   * 查询所有可达攻击路径
   */
  public static List<AttackPath> selectAllattackPaths(){
    AttackPathExample example = new AttackPathExample();
    AttackPathExample.Criteria criteria = example.createCriteria();
    criteria.andSrcIsNotNull();
    return MapTool.attackPathMapper.selectByExample(example);
  }
  /**
   * 根据设备编号和威胁编号查询单机测试结果
   *
   * @param deviceId 设备编号
   * @param threatId 威胁编号
   * @param variant  加密状态
   * @return 单机测试结果
   */
  public static SingleTest selectSingleTestByDeviceIdAndThreatId(Integer deviceId, Integer threatId, Integer variant) {
    SingleTestExample example = new SingleTestExample();
    SingleTestExample.Criteria criteria = example.createCriteria();

    /* 编号不为空 */
    if (deviceId != null && threatId != null && variant != null) {
      criteria.andDeviceIdEqualTo(deviceId);
      criteria.andThreatIdEqualTo(threatId);
      criteria.andVariantIdEqualTo(variant);
    } else return null;

    /* 查询单机测试结果 */
    List<SingleTest> st = MapTool.singleTestMapper.selectByExample(example);

    if (st == null || st.size() == 0) return null;
    else return st.get(0); /* 唯一，获取第一个 */
  }

  /**
   * 根据可达路径和威胁编号查询业务设备对测试用例更新
   *
   * @param tc       待更新数据
   * @param path     可达路径
   * @param threatId 威胁编号
   * @return 真 表示更新成功，假 表示更新失败
   */
  public static boolean updateServiceTestCaseSelectiveByPathAndThreatId(ServiceTestcase
                                                                          tc, List<Node> path, Integer threatId) {
    ServiceTestcaseExample example = new ServiceTestcaseExample();
    ServiceTestcaseExample.Criteria criteria = example.createCriteria();

    /* 任何不为空 */
    if (tc == null || path == null) return false;

    /* 获得第一个和最后一个数据流 */
    DataFlow first = path.get(0).getDataflow(), last = path.get(path.size() - 1).getDataflow();

    criteria.andSrcEqualTo(first.getSrc());
    criteria.andSportEqualTo(first.getSport());
    criteria.andDstEqualTo(first.getDst());
    criteria.andDportEqualTo(first.getDport());
    criteria.andProtocolEqualTo(first.getProtocol());
    criteria.andPrivilegesEqualTo(StringUtils.join(last.getPrivileges(), ","));
    criteria.andVariantIdEqualTo(first.getVariant());
    criteria.andThreatIdEqualTo(threatId);

    /* 更新数据库 */
    return MapTool.serviceTestcaseMapper.updateByExampleSelective(tc, example) > 0;
  }

  /**
   * 根据可达路径查询测试用例结果
   *
   * @param path 可达路径
   * @return 对应的测试用例结果
   */
  public static List<ServiceTestcase> selectServiceTestcaseByPath(List<Node> path) {
    ServiceTestcaseExample example = new ServiceTestcaseExample();
    ServiceTestcaseExample.Criteria criteria = example.createCriteria();

    /* 路径不为空 */
    if (path == null) return null;

    /* 获得第一个和最后一个数据流 */
    DataFlow first = path.get(0).getDataflow(), last = path.get(path.size() - 1).getDataflow();

    criteria.andSrcEqualTo(first.getSrc());
    criteria.andSportEqualTo(first.getSport());
    criteria.andDstEqualTo(first.getDst());
    criteria.andDportEqualTo(first.getDport());
    criteria.andProtocolEqualTo(first.getProtocol());
    criteria.andPrivilegesEqualTo(StringUtils.join(last.getPrivileges(), ","));
    criteria.andVariantIdEqualTo(first.getVariant());

    return MapTool.serviceTestcaseMapper.selectByExample(example);
  }
  /**
   *
   * 根据漏洞ID查询攻击模板
   *
  *
   * @return*/
  public static List<AttackModel> selectModelByVulnID(int threatID){
    AttackModelExample example = new AttackModelExample();
    AttackModelExample.Criteria criteria = example.createCriteria();
    criteria.andVulnIdEqualTo(threatID);
    return MapTool.attackModelMapper.selectByExample(example);
  }
  /**
   *
   * 根据漏洞ID查询前置条件
   *
   *
   * @return*/
  public static List<AttackModel> SelectPrePriByVulnID(int threatID){
    AttackModelExample example = new AttackModelExample();
    AttackModelExample.Criteria criteria = example.createCriteria();
    criteria.andVulnIdEqualTo(threatID);
    return MapTool.attackModelMapper.selectByExample(example);
  }
 /**
   * 统计安全威胁总数
   *
   * @return 安群威胁总数
   */
  public static Integer countSecurityThreat() {
    return (int) MapTool.securityThreatMapper.countByExample(new SecurityThreatExample());
  }

  public static void RiseUserPrivilege(int up_p) {
    /*根据权限ID读取权限对应的device-id device-service*/
    DevicePrivilegeExample DP = new DevicePrivilegeExample();
    DevicePrivilegeExample.Criteria criteria = DP.createCriteria();
    criteria.andPrivilegeIdEqualTo(up_p);
    List<DevicePrivilege> PrivilegeInfo= MapTool.devicePrivilegeMapper.selectByExample(DP);

    /*循环写入权限，判断当前权限没有就写入*/

    for (int i=0;i<PrivilegeInfo.size();i++) {
        UserPrivilegeKey key = new UserPrivilegeKey();
        key.setDeviceId(PrivilegeInfo.get(i).getDeviceId());
        key.setServiceId(PrivilegeInfo.get(i).getServiceId());
        key.setPrivilegeId(PrivilegeInfo.get(i).getPrivilegeId());
        MapTool.userPrivilegeMapper.insert(key);
        MapTool.commit();
    }
  }

  /**
   * 判断当前权限是否已经拥有
   *
   * @return*/
  public static boolean selectUserPrivilegeExists(int privilege_id) {
    UserPrivilegeExample example = new UserPrivilegeExample();
    UserPrivilegeExample.Criteria criteria1= example.createCriteria();
    criteria1.andPrivilegeIdEqualTo(privilege_id);
    //如果存在则返回true，不存在则返回false
    if (MapTool.userPrivilegeMapper.selectByExample(example).size()>0)
      return  true;
    else
      return  false;
  }

  /**
   * 利用过的漏洞标志位写为1
   * @param vulnId
   */
  public static void UpdateVulnFlag(int vulnId) {
     AttackModelExample example = new AttackModelExample();
     AttackModelExample.Criteria criteria = example.createCriteria();
     criteria.andVulnIdEqualTo(vulnId);

     AttackModel data = new AttackModel();
     data.setUsedFlag(1);
     MapTool.attackModelMapper.updateByExampleSelective(data,example);
     MapTool.commit();
  }
/**
 *查找该漏洞是否被利用
 */
  public static boolean searchVulnFlag(int vulnid) {
    AttackModelExample example = new AttackModelExample();
    AttackModelExample.Criteria criteria = example.createCriteria();
    criteria.andVulnIdEqualTo(vulnid);
    List<AttackModel> a = MapTool.attackModelMapper.selectByExample(example);
    if (a.size()!=0&&a.get(0).getUsedFlag()==1)
      return true;
    else
      return false;
  }

  /**
   * 通过IP地址查device_id
   * @param ip
   * @return
   */
  public static List<DeviceNetwork> SelectIdByIp(String ip){
    DeviceNetworkExample deviceExample = new DeviceNetworkExample();
    DeviceNetworkExample.Criteria criteria = deviceExample.createCriteria();
    criteria.andAddressEqualTo(ip);
    return MapTool.deviceNetworkMapper.selectByExample(deviceExample);
  }}
