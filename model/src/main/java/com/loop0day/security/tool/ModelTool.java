/*
 * @author: loop0day
 * @date: 2020/3/19
 * @description: 支持评估的模型工具类
 */

package com.loop0day.security.tool;

import com.loop0day.security.bean.Node;
import com.loop0day.security.conf.Config;
import com.loop0day.security.core.SecuritySystem;
import com.loop0day.security.db.bean.Device;
import com.loop0day.security.db.bean.SecurityFunction;
import com.loop0day.security.db.bean.SecurityThreat;
import com.loop0day.security.db.bean.ServiceTestcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelTool {
  /**
   * 获取所有安全设备编号
   *
   * @return 所有安全设备编号列表
   */
  public static List<Integer> getAllSecurityDeviceIds() {
    List<Integer> ids = new ArrayList<>();
    List<Device> devs = MapTool.selectSecurityDevice();

    for (Device dev : devs) ids.add(dev.getDeviceId());

    return ids;
  }

  /**
   * 获取所有设备的资产价值
   *
   * @return 所有设备的资产价值映射表
   */
  public static Map<Integer, Double> getDeviceValues() {
    Map<Integer, Double> maps = new HashMap<>();
    List<Device> devs = MapTool.selectAllDevice();

    for (Device dev : devs) maps.put(dev.getDeviceId(), dev.getDeviceValue());

    return maps;
  }

  /**
   * 获取安全功能威胁映射表
   *
   * @return 获取安全功能威胁映射表
   */
  public static Map<Integer, List<Integer>> getThreatsMap() {
    Map<Integer, List<Integer>> maps = new HashMap<>();
    List<SecurityFunction> funcs = MapTool.selectAllSecurityFunction();

    for (SecurityFunction func : funcs) {
      List<Integer> lst = new ArrayList<>();

      for (SecurityThreat t : MapTool.selectSecurityThreatByFunctionId(func.getFunctionId())) {
        lst.add(t.getThreatId());
      }

      maps.put(func.getFunctionId(), lst);
    }

    return maps;
  }

  /**
   * 查询响应结果
   *
   * @param system  系统对象
   * @param exclude 排除列表
   * @return 响应结果
   */
  public static Double[][][] getTRS(SecuritySystem system, List<Integer> exclude) {
    system.analyzeServiceResult(exclude);

    List<List<Node>> paths = system.getServicesPaths();
    Double[][][] result = new Double[paths.size()][][];

    for (int i = 0; i < paths.size(); ++i) {
      Double[][] r = new Double[MapTool.countSecurityThreat()][4];

      for (ServiceTestcase st : MapTool.selectServiceTestcaseByPath(paths.get(i))) {
        /* 记录率 */
        r[st.getThreatId() - 1][0] = st.getRecord();
        /* 检测率 */
        r[st.getThreatId() - 1][1] = st.getDetect();
        /* 告警率 */
        r[st.getThreatId() - 1][2] = st.getAlarm();
        /* 阻断率 */
        r[st.getThreatId() - 1][3] = st.getBlock();
      }

      result[i] = r;
    }

    return result;
  }

  /**
   * 获取可达路径资产价值表
   *
   * @param system 安全系统对象，承载可达路径
   * @return 可达路径资产价值表
   */
  public static Double[] getPathValues(SecuritySystem system) {
    List<List<Node>> paths = system.getServicesPaths();

    Double[] values = new Double[paths.size()];

    for (int i = 0; i < paths.size(); ++i) {
      List<Node> path = paths.get(i);
      Device dev = MapTool.selectDeviceByDeviceId(path.get(path.size() - 1).getDeviceId());

      values[i] = dev.getDeviceValue();
    }

    return values;
  }

  /**
   * 获取安全维度对应的安全功能编号集合
   *
   * @return 安全维度安全功能映射表
   */
  public static Map<String, List<Integer>> getSecurityFuncMap() {
    Map<String, List<Integer>> result = new HashMap<>();

    Config.SECURITY_DIMENSIONS.forEach((dimension) -> {
      List<Integer> ids = new ArrayList<>();
      List<SecurityFunction> sfs = MapTool.selectSecurityFunctionBySecurityClass(dimension);

      if (sfs.size() > 0) {
        sfs.forEach(sf -> {
          ids.add(sf.getFunctionId());
        });
      }

      result.put(dimension, ids);
    });

    return result;
  }
}
