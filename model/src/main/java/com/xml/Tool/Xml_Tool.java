package com.xml.Tool;


import com.loop0day.security.bean.Node;
import com.loop0day.security.core.SecuritySystem;
import com.loop0day.security.db.bean.*;
import com.loop0day.security.db.dao.*;
import com.xml.Conf.Config;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class Xml_Tool {
  /* 设置XML输出的格式 */
  private static final OutputFormat format;
  /* 数据库会话对象 */
  private static SqlSession session = null;
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
  /* 指明载荷状态对象 */
  private static SecurityVariantMapper securityVariantMapper;
  /*攻击模板*/
  private static AttackModelMapper attackModelMapper;

  static {
    /* XML输出格式编码 */
    format = OutputFormat.createPrettyPrint();
    format.setEncoding("UTF-8");
  }

  static {
    try {
      /* 数据库会话工厂对象 */
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(Config.MYBATIS_CONF_PATH));
      /* 初始化数据库会话对象 */
      Xml_Tool.session = sqlSessionFactory.openSession();

    } catch (IOException e) {
      e.printStackTrace();
    }

    /* 断言数据库会话对象不为空 */
    assert session != null;

    /* 初始化设备基础信息对象 */
    Xml_Tool.deviceMapper = session.getMapper(DeviceMapper.class);
    /* 初始化设备连接关系对象 */
    Xml_Tool.deviceConnectMapper = session.getMapper(DeviceConnectMapper.class);
    /* 初始化网络信息映射对象 */
    Xml_Tool.deviceNetworkMapper = session.getMapper(DeviceNetworkMapper.class);
    /* 初始化设备路由规则映射对象 */
    Xml_Tool.deviceRouteMapper = session.getMapper(DeviceRouteMapper.class);
    /* 初始化设备数据流规则映射对象 */
    Xml_Tool.deviceDataflowMapper = session.getMapper(DeviceDataflowMapper.class);
    /* 初始化设备服务信息映射对象 */
    Xml_Tool.deviceServiceMapper = session.getMapper(DeviceServiceMapper.class);
    /* 初始化设备权限信息映射对象 */
    Xml_Tool.devicePrivilegeMapper = session.getMapper(DevicePrivilegeMapper.class);
    /* 初始化用户权限映射对象 */
    Xml_Tool.userPrivilegeMapper = session.getMapper(UserPrivilegeMapper.class);
    /* 初始化安全功能映射对象 */
    Xml_Tool.securityFunctionMapper = session.getMapper(SecurityFunctionMapper.class);
    /* 初始化安全威胁映射对象 */
    Xml_Tool.securityThreatMapper = session.getMapper(SecurityThreatMapper.class);
    /* 初始化安全设备安全功能映射对象 */
    Xml_Tool.deviceSecurityMapper = session.getMapper(DeviceSecurityMapper.class);
    /* 初始化业务设备威胁映射对象 */
    Xml_Tool.deviceThreatMapper = session.getMapper(DeviceThreatMapper.class);
    /* 初始化业务设备对测试用例映射对象 */
    Xml_Tool.serviceTestcaseMapper = session.getMapper(ServiceTestcaseMapper.class);
    /* 初始化单机测试映射对象 */
    Xml_Tool.singleTestMapper = session.getMapper(SingleTestMapper.class);
    /* 载荷状态对象 */
    Xml_Tool.securityVariantMapper = session.getMapper(SecurityVariantMapper.class);
    /* 创建一个mapper*/
    Xml_Tool.attackModelMapper =session.getMapper(AttackModelMapper.class);
  }

  /**
   * 关闭数据库会话
   */
  public static void close() {
    Xml_Tool.session.close();
  }

  public static void XmlWrite() throws IOException, DocumentException {
//    Xml_Tool.init_sql();
    Xml_Tool.SecurityFunction();
    Xml_Tool.SecurityThreat();
//    Xml_Tool.SecurityVariant();
    Xml_Tool.DeviceInfoXmlMysql();
    Xml_Tool.DeviceNetworkXMLMysql();
    Xml_Tool.PhyConnectXMLMysql();
    Xml_Tool.NetRouteXMLMysql();
    Xml_Tool.DeviceService();
    Xml_Tool.DevicePrivilege();
    Xml_Tool.DeviceDataFlow();
    Xml_Tool.userPrivilege();
    Xml_Tool.DeviceSecurity();
    Xml_Tool.DeviceThreat();
    //目前的单机测试结果采用的是xml导入，并不是前端导入
    Xml_Tool.SignleTest();
    Xml_Tool.AttackModel();
  }



  /**
   * 以下是写入数据库的函数
   * 初始化数据表
   */
  private static void init_sql() throws IOException {
    ScriptRunner scriptRunner = new ScriptRunner(Xml_Tool.session.getConnection());
    Resources.setCharset(StandardCharsets.UTF_8);
    scriptRunner.runScript(new FileReader(Config.Init_sql));
  }

  /**
   * XML_TO_MYSQL
   */

  private static void SecurityFunction() throws DocumentException {
    SecurityFunction deviceExample = new SecurityFunction();
    SAXReader reader = new SAXReader();
    File xml_file = new File(Config.SecurityFuncXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析SecurityFunc.xml...");
    String[][] device = new String[3][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/function_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/function_name"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/security_class"));

    if (device[0].length != device[1].length || device[0].length != device[2].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setFunctionId(Integer.parseInt(device[0][i]));
        deviceExample.setFunctionName(device[1][i]);
        deviceExample.setSecurityClass(device[2][i]);
        Xml_Tool.securityFunctionMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void SecurityThreat() throws DocumentException {
    SecurityThreat deviceExample = new SecurityThreat();
    SAXReader reader = new SAXReader();
    File xml_file = new File(Config.SecurityThreatXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析SecurityThreat.xml...");
    String[][] device = new String[3][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/threat_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/threat_name"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/function_id"));

    if (device[0].length != device[1].length || device[0].length != device[2].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setThreatId(Integer.parseInt(device[0][i]));
        deviceExample.setThreatName(device[1][i]);
        deviceExample.setFunctionId(Integer.parseInt(device[2][i]));
        Xml_Tool.securityThreatMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void SecurityVariant() throws DocumentException {
    SecurityVariant deviceExample = new SecurityVariant();
    SAXReader reader = new SAXReader();
    File xml_file = new File(Config.SecurityVariantXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析SecurityVariant.xml...");
    String[][] device = new String[2][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/variant_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/variant_name"));
    if (device[0].length != device[1].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setVariantId(Integer.parseInt(device[0][i]));
        deviceExample.setVariantName(device[1][i]);
        Xml_Tool.securityVariantMapper.insert(deviceExample);
        Xml_Tool.commit();
      }

    }

  }

  private static void DeviceInfoXmlMysql() throws DocumentException {
    SAXReader reader = new SAXReader();
    File xml_file = new File(Config.DeviceInfoXML);
    Document document = reader.read(xml_file);
    Device deviceExample = new Device();
    List<Node> device_id = document.selectNodes("/DATA/ROW/device_id");
    List<Node> device_name = document.selectNodes("/DATA/ROW/device_name");
    List<Node> device_type = document.selectNodes("/DATA/ROW/device_type");
    List<Node> device_deploy = document.selectNodes("/DATA/ROW/device_deploy");
    List<Node> device_value = document.selectNodes("/DATA/ROW/device_value");
    System.out.println("开始解析device_info.xml...");
    String[][] device = new String[5][];
    device[0] = it_to_arr(device_id);
    device[1] = it_to_arr(device_name);
    device[2] = it_to_arr(device_type);
    device[3] = it_to_arr(device_deploy);
    device[4] = it_to_arr(device_value);

    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length || device[0].length != device[4].length)
      System.out.println("校验失败！");
    else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setDeviceName(device[1][i]);
        deviceExample.setDeviceType(device[2][i]);
        deviceExample.setDeviceDeploy(Integer.parseInt(device[3][i]));
        deviceExample.setDeviceValue(Double.valueOf(device[4][i]));
        Xml_Tool.deviceMapper.insert(deviceExample);
        Xml_Tool.commit();
      }

    }

  }

  private static void DeviceNetworkXMLMysql() throws DocumentException {
    DeviceNetwork deviceExample = new DeviceNetwork();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.DeviceNetworkXML);
    File xml_file = new File(Config.DeviceNetworkXML);
    Document document = reader.read(xml_file);

    System.out.println("开始解析device_network.xml...");

    String[][] device = new String[4][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/interface_id"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/address"));
    device[3] = it_to_arr(document.selectNodes("/DATA/ROW/netmask"));

    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length)
      System.out.println("校验失败！");
    else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setInterfaceId(Integer.parseInt(device[1][i]));
        deviceExample.setAddress(device[2][i]);
        deviceExample.setNetmask(device[3][i]);
        Xml_Tool.deviceNetworkMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void PhyConnectXMLMysql() throws DocumentException {
    DeviceConnect deviceExample = new DeviceConnect();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.DeviceConnectXML);
    File xml_file = new File(Config.DeviceConnectXML);
    Document document = reader.read(xml_file);
    List<Node> device_id = document.selectNodes("/DATA/ROW/device_id");
    List<Node> interface_id = document.selectNodes("/DATA/ROW/interface_id");
    List<Node> peer_device_id = document.selectNodes("/DATA/ROW/peer_device_id");
    List<Node> peer_interface_id = document.selectNodes("/DATA/ROW/peer_interface_id");

    System.out.println("开始解析device_phyConnect.xml...");
    String[][] device = new String[4][];
    device[0] = it_to_arr(device_id);
    device[1] = it_to_arr(interface_id);
    device[2] = it_to_arr(peer_device_id);
    device[3] = it_to_arr(peer_interface_id);

    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length)
      System.out.println("校验失败！");
    else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setInterfaceId(Integer.parseInt(device[1][i]));
        deviceExample.setPeerDeviceId(Integer.parseInt(device[2][i]));
        deviceExample.setPeerInterfaceId(Integer.parseInt(device[3][i]));
        Xml_Tool.deviceConnectMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }
  }

  private static void NetRouteXMLMysql() throws DocumentException {
    DeviceRoute deviceExample = new DeviceRoute();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.DeviceRouteXML);
    File xml_file = new File(Config.DeviceRouteXML);
    Document document = reader.read(xml_file);

    System.out.println("开始解析device_route.xml...");
    String[][] device = new String[5][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/destination"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/genmask"));
    device[3] = it_to_arr(document.selectNodes("/DATA/ROW/gateway"));
    device[4] = it_to_arr(document.selectNodes("/DATA/ROW/interface_id"));

    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length || device[0].length != device[4].length)
      System.out.println("校验失败！");
    else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setDestination(device[1][i]);
        deviceExample.setGenmask(device[2][i]);
        deviceExample.setGateway(device[3][i]);
        deviceExample.setInterfaceId(Integer.parseInt(device[4][i]));
        Xml_Tool.deviceRouteMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void DeviceService() throws DocumentException {
    DeviceService deviceExample = new DeviceService();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.DeviceServiceXML);
    File xml_file = new File(Config.DeviceServiceXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析device_service.xml...");
    String[][] device = new String[6][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/service_id"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/enviroment"));
    device[3] = it_to_arr(document.selectNodes("/DATA/ROW/port"));
    device[4] = it_to_arr(document.selectNodes("/DATA/ROW/service"));
    device[5] = it_to_arr(document.selectNodes("/DATA/ROW/protocol"));

    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length || device[0].length != device[4].length || device[0].length != device[5].length)
      System.out.println("校验失败！");
    else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setServiceId(Integer.parseInt(device[1][i]));
        deviceExample.setEnvironment(device[2][i]);
        deviceExample.setPort(device[3][i]);
        deviceExample.setService(device[4][i]);
        deviceExample.setProtocol(device[5][i]);
        Xml_Tool.deviceServiceMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }
  }

  private static void DevicePrivilege() throws DocumentException {
    DevicePrivilege deviceExample = new DevicePrivilege();

    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.DeivcePrivilegeXML);
    File xml_file = new File(Config.DeivcePrivilegeXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析device_privilege.xml...");
    String[][] device = new String[5][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/service_id"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/privilege_id"));
    device[3] = it_to_arr(document.selectNodes("/DATA/ROW/data"));
    device[4] = it_to_arr(document.selectNodes("/DATA/ROW/comment"));
    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length || device[0].length != device[4].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setServiceId(Integer.parseInt(device[1][i]));
        deviceExample.setPrivilegeId(Integer.parseInt(device[2][i]));
        deviceExample.setData(device[3][i]);
        deviceExample.setComment(device[4][i]);
        Xml_Tool.devicePrivilegeMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void DeviceDataFlow() throws DocumentException {
    DeviceDataflow deviceExample = new DeviceDataflow();

    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.DeviceDataflowXML);
    File xml_file = new File(Config.DeviceDataflowXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析device_dataflow.xml...");
    String[][] device = new String[17][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/type"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/privilege_id"));
    device[3] = it_to_arr(document.selectNodes("/DATA/ROW/src"));
    device[4] = it_to_arr(document.selectNodes("/DATA/ROW/sport"));
    device[5] = it_to_arr(document.selectNodes("/DATA/ROW/dst"));
    device[6] = it_to_arr(document.selectNodes("/DATA/ROW/dport"));
    device[7] = it_to_arr(document.selectNodes("/DATA/ROW/protocol"));
    device[8] = it_to_arr(document.selectNodes("/DATA/ROW/variant_id"));
    device[9] = it_to_arr(document.selectNodes("/DATA/ROW/t_privilege_id"));
    device[10] = it_to_arr(document.selectNodes("/DATA/ROW/t_src"));
    device[11] = it_to_arr(document.selectNodes("/DATA/ROW/t_sport"));
    device[12] = it_to_arr(document.selectNodes("/DATA/ROW/t_dst"));
    device[13] = it_to_arr(document.selectNodes("/DATA/ROW/t_dport"));
    device[14] = it_to_arr(document.selectNodes("/DATA/ROW/t_protocol"));
    device[15] = it_to_arr(document.selectNodes("/DATA/ROW/t_variant_id"));
    device[16] = it_to_arr(document.selectNodes("/DATA/ROW/priority"));
    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length || device[0].length != device[4].length || device[0].length != device[5].length || device[0].length != device[6].length
      || device[0].length != device[7].length || device[0].length != device[8].length || device[0].length != device[9].length || device[0].length != device[10].length || device[0].length != device[11].length || device[0].length != device[12].length || device[0].length != device[13].length || device[0].length != device[14].length || device[0].length != device[15].length || device[0].length != device[16].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setType(device[1][i]);
        deviceExample.setPrivilegeId(Integer.parseInt(device[2][i]));
        deviceExample.setSrc(device[3][i]);
        deviceExample.setSport(device[4][i]);
        deviceExample.setDst(device[5][i]);
        deviceExample.setDport(device[6][i]);
        deviceExample.setProtocol(device[7][i]);
        deviceExample.setVariantId(Integer.parseInt(device[8][i]));
        deviceExample.settPrivilegeId(Integer.parseInt(device[9][i]));
        deviceExample.settSrc(device[10][i]);
        deviceExample.settSport(device[11][i]);
        deviceExample.settDst(device[12][i]);
        deviceExample.settDport(device[13][i]);
        deviceExample.settProtocol(device[14][i]);
        deviceExample.settVariantId(Integer.parseInt(device[15][i]));
        deviceExample.setPriority(Integer.parseInt(device[16][i]));
        Xml_Tool.deviceDataflowMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void userPrivilege() throws DocumentException {
    UserPrivilegeKey deviceExample = new UserPrivilegeKey();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.UserPrivilegeXML);
    File xml_file = new File(Config.UserPrivilegeXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析User_privilege.xml...");
    String[][] device = new String[3][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/service_id"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/privilege_id"));


    if (device[0].length != device[1].length || device[0].length != device[2].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setServiceId(Integer.parseInt(device[1][i]));
        deviceExample.setPrivilegeId(Integer.parseInt(device[2][i]));
        Xml_Tool.userPrivilegeMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void DeviceSecurity() throws DocumentException {

    DeviceSecurityKey deviceExample = new DeviceSecurityKey();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.DeviceSecurityXML);
    File xml_file = new File(Config.DeviceSecurityXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析DeviceSecurity.xml...");
    String[][] device = new String[3][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/function_id"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/owner_id"));
    if (device[0].length != device[1].length || device[0].length != device[2].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setFunctionId(Integer.parseInt(device[1][i]));
        deviceExample.setOwnerId(Integer.parseInt(device[2][i]));
        Xml_Tool.deviceSecurityMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void DeviceThreat() throws DocumentException {

    DeviceThreat deviceExample = new DeviceThreat();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.DeviceThreatXML);
    File xml_file = new File(Config.DeviceThreatXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析DeviceThreat.xml...");
    String[][] device = new String[5][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/service_id"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/privilege_id"));
    device[3] = it_to_arr(document.selectNodes("/DATA/ROW/threat_id"));
    device[4] = it_to_arr(document.selectNodes("/DATA/ROW/threat_weight"));

    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length || device[0].length != device[4].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setServiceId(Integer.parseInt(device[1][i]));
        deviceExample.setPrivilegeId(Integer.parseInt(device[2][i]));
        deviceExample.setThreatId(Integer.parseInt(device[3][i]));
        deviceExample.setThreatWeight(Double.valueOf(device[4][i]));
        Xml_Tool.deviceThreatMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }

  private static void SignleTest() throws DocumentException {
    SingleTest deviceExample = new SingleTest();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.SignleTestXML);
    File xml_file = new File(Config.SignleTestXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析SignleTest.xml...");
    String[][] device = new String[8][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/device_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/function_id"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/threat_id"));
    device[3] = it_to_arr(document.selectNodes("/DATA/ROW/variant_id"));
    device[4] = it_to_arr(document.selectNodes("/DATA/ROW/record"));
    device[5] = it_to_arr(document.selectNodes("/DATA/ROW/detect"));
    device[6] = it_to_arr(document.selectNodes("/DATA/ROW/alarm"));
    device[7] = it_to_arr(document.selectNodes("/DATA/ROW/block"));
    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length || device[0].length != device[4].length || device[0].length != device[5].length || device[0].length != device[6].length || device[0].length != device[7].length) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
        deviceExample.setDeviceId(Integer.parseInt(device[0][i]));
        deviceExample.setFunctionId(Integer.parseInt(device[1][i]));
        deviceExample.setThreatId(Integer.parseInt(device[2][i]));
        deviceExample.setVariantId(Integer.parseInt(device[3][i]));
        deviceExample.setRecord(Double.valueOf(device[4][i]));
        deviceExample.setDetect(Double.valueOf(device[5][i]));
        deviceExample.setAlarm(Double.valueOf(device[6][i]));
        deviceExample.setBlock(Double.valueOf(device[7][i]));
        Xml_Tool.singleTestMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }

  }
  /**
   * 攻击模板数据导入
   */
  private static void AttackModel() throws DocumentException {
    AttackModel deviceExample = new AttackModel();
    SAXReader reader = new SAXReader();
//    Document document = reader.read(Config.AttackModelXML);
    File xml_file = new File(Config.AttackModelXML);
    Document document = reader.read(xml_file);
    System.out.println("开始解析AttackModel.xml...");
    String[][] device = new String[4][];
    device[0] = it_to_arr(document.selectNodes("/DATA/ROW/vuln_id"));
    device[1] = it_to_arr(document.selectNodes("/DATA/ROW/vuln_name"));
    device[2] = it_to_arr(document.selectNodes("/DATA/ROW/pre_privilege_id"));
    device[3] = it_to_arr(document.selectNodes("/DATA/ROW/fin_privilege_id"));
    if (device[0].length != device[1].length || device[0].length != device[2].length || device[0].length != device[3].length ) {
      System.out.println("校验失败！");
      for (int i = 0; i < device.length; i++)
        System.out.println(device[i].length);
    } else {
      System.out.println("校验成功！");
      for (int i = 0; i < device[0].length; i++) {
          deviceExample.setVulnId(Integer.parseInt(device[0][i]));
          deviceExample.setVulnName(device[1][i]);
          deviceExample.setPrePrivilegeId(Integer.parseInt(device[2][i]));
          deviceExample.setFinPrivilegeId(Integer.parseInt(device[3][i]));
          deviceExample.setUsedFlag(0);
          Xml_Tool.attackModelMapper.insert(deviceExample);
      }
      Xml_Tool.commit();
    }
  }
  /**
   * 将nodes转成arr类型
   *
   * @return arr
   */
  private static String[] it_to_arr(List<Node> list) {
    int i = 0;
    Iterator<Node> it = list.iterator();
    while (it.hasNext()) {
      Element e = (Element) it.next();
      if (StringUtils.isNoneEmpty(e.getText())) {
        //获取list长度
        i++;
      }
    }
    String[] arr = new String[i];
    i = 0;
    it = list.iterator();
    while (it.hasNext()) {
      Element e = (Element) it.next();
      if (StringUtils.isNoneEmpty(e.getText())) {
        //给arr赋值
        arr[i] = e.getText();
        i++;
      }
    }
    return arr;
  }

  /**
   * 修改数据库后提交
   */
  public static void commit() {
    Xml_Tool.session.commit();
  }

  public static void setAttackModelMapper(AttackModelMapper attackModelMapper) {
    Xml_Tool.attackModelMapper = attackModelMapper;
  }
}
