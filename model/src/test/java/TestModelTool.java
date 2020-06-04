/*
 * @author: loop0day
 * @date: 2020/3/19
 * @description: 测试类
 */

import com.loop0day.security.core.SecuritySystem;
import com.loop0day.security.tool.ModelTool;

import java.util.ArrayList;
import java.util.List;

public class TestModelTool {
  public static void main(String[] args) {

    SecuritySystem system = new SecuritySystem();

    system.calcServicePaths();
    /* 仅需第一次执行时调用，用于数据库生成测试用例，之后注释掉再执行 */
    //     system.makeServiceTestCase();

    /* 测试：获取所有安全设备编号 */
    // List<Integer> securityDeviceIds = ModelTool.getAllSecurityDeviceIds();

    /* 测试：获取所有设备资产价值 */
    // Map<Integer, Double> deviceValues = ModelTool.getDeviceValues();

    /* 测试：获取安全功能威胁映射表 */
    //     Map<Integer, List<Integer>> threatMaps = ModelTool.getThreatsMap();
    //     for(Integer secuId:threatMaps.keySet()){
    //       System.out.println(secuId+" "+threatMaps.get(secuId));
    //     }
    //

    //
    //    int len=27;
    //    System.out.print("[");
    //    for(int i=0;i<len;i++){
    //      System.out.print((double)1/(double)len);
    //      if(i<len-1)
    //        System.out.print(",");
    //    }
    //    System.out.print("]");

    /* 测试：获取可达路径资产价值表 */
    //      System.out.println(system.getServicesPaths());
    //     Double[] values = ModelTool.getPathValues(system);
    //     System.out.println(values.length);
    //     for(Double value:values)
    //       System.out.print(value+" ");




    /* 测试：获取响应结果表 */
    List<Integer> exclude = new ArrayList<>();
    Double[][][] trs0 = ModelTool.getTRS(system, exclude);
    for (int i = 0; i < trs0.length; i++) {
      System.out.println(i + "================路径开始=======================");
      for (int j = 0; j < trs0[i].length; j++) {
        for (int k = 0; k < trs0[i][j].length; k++) {
          System.out.print(trs0[i][j][k] + " ");
        }
        System.out.println();
      }
      System.out.println(i + "================路径结束=======================");

    }

    // exclude.add(7);
    // Double[][][] trs1 = ModelTool.getTRS(system, exclude);

    // Map<String, List<Integer>> SFMap = ModelTool.getSecurityFuncMap();
  }
}
