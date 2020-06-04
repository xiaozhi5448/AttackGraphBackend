/*
 * @author: loop0day
 * @date: 2020/3/4
 * @description: 测试类
 */

import com.loop0day.security.core.SecuritySystem;

import java.util.ArrayList;
import java.util.List;

public class Test {
  public static void main(String[] args) throws Exception {
    List<Integer> exclude = new ArrayList<>();
    SecuritySystem system = new SecuritySystem();
    system.importXMLData();
    system.buildAttackPath();
  }
}
