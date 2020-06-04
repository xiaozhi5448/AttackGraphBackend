/*
 * @author: loop0day
 * @date: 2020/3/13
 * @description: 全局配置类
 */

package com.loop0day.security.conf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
  /* Mybatis配置文件名称 */
  public static final String MYBATIS_CONF_PATH = "mybatis-config.xml";

  /* 默认加密状态 */
  public static final Integer DATAFLOW_DEFAULT_VARIANT = 0;

  /* 业务设备类型字符串前缀 */
  public static final String DEVICE_TYPE_SERVICE_PREFIX = "业务";
  /* 网络设备类型字符串前缀 */
  public static final String DEVICE_TYPE_NETWORK_PREFIX = "网络";
  /* 安全设备类型字符串 */
  public static final String DEVICE_TYPE_SECURITY_STR = "安全";
  /* 交换机设备名称前缀 */
  public static final String DEVICE_NAME_SWITCH_PREFIX = "交换机";

  /* 空网络地址 */
  public static final String NET_NULL_ADDRESS = "-";
  /* 空子网掩码 */
  public static final String NET_NULL_NETMASK = "-";
  /* 私有网段 */
//  public static final List<String> NET_PRIVATE_ADDRESSES = new ArrayList<>(Arrays.asList());
   public static final List<String> NET_PRIVATE_ADDRESSES = new ArrayList<>(Arrays.asList("10.0.0.0", "10.1.0.0", "172.16.0.0", "172.16.1.0"));
  // public static final List<String> NET_PRIVATE_ADDRESSES = new ArrayList<>(Arrays.asList("5.10.128.0", "5.20.40.0", "5.20.41.0", "10.26.1.0", "10.26.2.0", "10.26.3.0", "10.26.4.0", "10.26.5.0"));

  /* 路由规则错误码 */
  public static final Integer ROUTE_ERROR_CODE = -1;
  /* 当前设备即为路由规则的网关 */
  public static final String ROUTE_SELF_GATEWAY = "0.0.0.0";
  /* 默认路由地址 */
  public static final String ROUTE_DEFAULT_DESTINATION = "0.0.0.0";

  /* 数据流规则通配符 */
  public static final String DATAFLOW_WILDCARD = "*";
  /* 数据流规则加密状态通配符 */
  public static final Integer DATAFLOW_VARIANT_WILDCARD = -1;
  /* 数据流规则权限通配符 */
  public static final Integer DATAFLOW_PRIVILEGE_WILDCARD = -1;
  /* 数据流规则空值 */
  public static final String DATAFLOW_NULL_VALUE = "-";
  /* 数据流规则加密状态空值 */
  public static final Integer DATAFLOW_VARIANT_NULL_VALUE = -1;
  /* 数据流规则权限空值 */
  public static final Integer DATAFLOW_PRIVILEGE_NULL_VALUE = -1;
  /* 数据流规则拒绝类型字符串 */
  public static final String DATAFLOW_DENY_STR = "deny";
  /* 数据流规则转发类型字符串 */
  public static final String DATAFLOW_TRANS_STR = "trans";
  /* 数据流规则接受类型字符串 */
  public static final String DATAFLOW_ACCEPT_STR = "accept";
  /* 数据流默认源端口 */
  public static final String DATAFLOW_DEFAULT_SRC_PORT = "65535";

  /* 设备服务空端口 */
  public static final String DEVICE_SERVICE_NULL_PORT = "-";
  /* 设备服务空协议 */
  public static final String DEVICE_SERVICE_NULL_PROTOCOL = "-";

  /* 主机安全字符串 */
  public static final String HOST_SECURITY_STR = "主机安全";
  /* 安全维度字符串 */
  public static final List<String> SECURITY_DIMENSIONS = new ArrayList<>(Arrays.asList("网络安全", "应用安全", "主机安全", "数据安全"));

  /* 业务设备威胁表设备通配符 */
  public static final Integer DEVICE_THREAT_DEVICE_WILDCARD = -1;
  /* 业务设备威胁表服务通配符 */
  public static final Integer DEVICE_THREAT_SERVICE_WILDCARD = -1;
  /* 业务设备威胁表权限通配符 */
  public static final Integer DEVICE_THREAT_PRIVILEGE_WILDCARD = -1;

  /* 单机测试率上限 */
  public static final Double SINGLE_TEST_UPPER_LIMIT = 1.0;

  /* 响应结果精度 */
  public static final Integer SERVICE_TESTCASE_RESULT_PRECISION = 2;
}
