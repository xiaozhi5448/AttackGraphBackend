/**
 * @author: loop0day
 * @date: 2020/3/6
 * @description: 可达路径节点类
 */

package com.loop0day.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node implements Cloneable {
  /* 设备编号 */
  private Integer deviceId;
  /* 到达当前设备时数据流状态 */
  private DataFlow dataflow;

  /**
   * 深拷贝可达路径节点
   *
   * @return 深度拷贝后的可达路径节点
   */
  @Override
  public Node clone() {
    Node node = null;

    try {
      node = (Node) super.clone();
      node.dataflow = this.dataflow.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    return node;
  }
}
