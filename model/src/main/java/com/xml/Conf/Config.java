package com.xml.Conf;


public class Config {

    //  public static final String OutputDir = System.getProperty("user.dir") + "..\\model\\src\\main\\resources\\xml\\standardOutput\\";
//  public static final String DrawXML = System.getProperty("user.dir") + "..\\model\\src\\main\\resources\\AttackChina-drawIO-v1.xml";
//  public static final String Init_sql = System.getProperty("user.dir") + "..\\model\\src\\main\\resources\\xml\\init.sql";
    public static final String OutputDir = "F:\\code\\java\\secure\\project\\model\\src\\main\\resources\\xml\\standardOutput\\";
    public static final String DrawXML = "F:\\code\\AttackChina-drawIO-v1.xml";
    public static final String Init_sql = "F:\\code\\java\\secure\\project\\model\\src\\main\\resources\\xml\\init.sql";
    /*drawIO数据表名称*/
    public static final String devTable = "设备信息";
    public static final String IfTable = "接口信息";
    public static final String PhyTable = "物理层";
    public static final String RouteTable = "网络层";
    public static final String DfTable = "数据流层";
    public static final String SysTable = "系统环境层";
    public static final String UserPTable = "测试权限表";
    public static final String SysSecFuncPTable = "全局安全功能分类表";
    public static final String SysSecThreatTable = "全局安全功能威胁映射表";
    public static final String DeviceSecFuncPTable = "设备安全功能表";
    public static final String DeviceSecThreatTable = "设备安全威胁表";
    public static final String AttackModel = "攻击模板";
    /*XML数据导入文件*/
    public static final String DeviceInfoXML = OutputDir + "device_info.xml";
    public static final String DeviceNetworkXML = OutputDir + "device_network.xml";
    public static final String DeviceConnectXML = OutputDir + "device_phyconnect.xml";
    public static final String DeviceRouteXML = OutputDir + "device_route.xml";
    public static final String DeviceServiceXML = OutputDir + "device_service.xml";
    public static final String DeviceDataflowXML = OutputDir + "device_dataflow.xml";
    public static final String DeivcePrivilegeXML = OutputDir + "device_privilege.xml";
    public static final String SecurityFuncXML = OutputDir + "security_function.xml";
    public static final String SecurityThreatXML = OutputDir + "security_threat.xml";
    public static final String SecurityVariantXML = OutputDir + "security_variant.xml";
    public static final String UserPrivilegeXML = OutputDir + "user_privilege.xml";
    public static final String DeviceSecurityXML = OutputDir + "device_security.xml";
    public static final String DeviceThreatXML = OutputDir + "device_threat.xml";
    public static final String SignleTestXML = OutputDir + "signle_test.xml";
    public static final String AttackModelXML = OutputDir + "attack_model.xml";
    /* Mybatis配置文件名称 */
    public static final String MYBATIS_CONF_PATH = "mybatis-config.xml";

}
