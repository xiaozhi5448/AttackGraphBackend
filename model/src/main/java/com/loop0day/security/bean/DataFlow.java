/**
 * @author: loop0day
 * @date: 2020/3/6
 * @description: 数据流类
 */

package com.loop0day.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFlow implements Cloneable {
  /* 源地址和源端口 */
  private String src, sport;
  /* 目的地址和目的端口 */
  private String dst, dport;
  /* 数据流协议 */
  private String protocol;
  /* 权限编号集合 */
  private Set<Integer> privileges;
  /* 变体状态 */
  private Integer variant;

  /**
   * 深拷贝函数
   *
   * @return 深拷贝后的数据流
   */
  @Override
  public DataFlow clone() {
    DataFlow dataflow = null;

    try {
      dataflow = (DataFlow) super.clone();
      dataflow.privileges = new HashSet<>(this.privileges);
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    return dataflow;
  }

  /**
   * 判断是否除了权限集合外都相等
   *
   * @param df 待比较数据流
   * @return 真表示相等，假表示不相等
   */
  public boolean isEqualExceptPrivileges(DataFlow df) {
    return this.getSrc().equals(df.getSrc())
      && this.getSport().equals(df.getSport())
      && this.getDst().equals(df.getDst())
      && this.getDport().equals(df.getDport())
      && this.getProtocol().toUpperCase().equals(df.getProtocol().toUpperCase())
      && this.getVariant().equals(df.getVariant());
  }
}
