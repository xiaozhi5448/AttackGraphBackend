package com.xml.Tool;

import com.loop0day.security.db.bean.AttackModel;
import com.mysql.cj.util.StringUtils;
import com.xml.Conf.Config;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 该类用于将draw.io的xml解析生成为标准格式的XML
 * */
public class StandXmlTool {
  private static final OutputFormat format;
  private static Object Node;
  public static int id;
  static {
    format = OutputFormat.createPrettyPrint();
    format.setEncoding("UTF-8");
  }

  /**
   * 初始化除单机测试xml之外的所有xml文件
   *
   * @throws IOException
   */
  public static void CreateXml() throws IOException {
    //初始化Xml
    List<File> fileList = new ArrayList<File>();
    fileList.add(new File(Config.DeviceInfoXML));
    fileList.add(new File(Config.DeviceNetworkXML));
    fileList.add(new File(Config.DeviceConnectXML));
    fileList.add(new File(Config.DeviceRouteXML));
    fileList.add(new File(Config.DeviceDataflowXML));
    fileList.add(new File(Config.DeviceServiceXML));
    fileList.add(new File(Config.DeivcePrivilegeXML));
    fileList.add(new File(Config.UserPrivilegeXML));
    fileList.add(new File(Config.SecurityFuncXML));
    fileList.add(new File(Config.DeviceThreatXML));
    fileList.add(new File(Config.DeviceSecurityXML));
    fileList.add(new File(Config.DeviceThreatXML));
    fileList.add(new File(Config.AttackModelXML));
//    fileList.add(new File(Config.SecurityVariantXML));
    fileList.add(new File(Config.SecurityThreatXML));
    for (int i = 0; i < fileList.size(); i++) {
      File file = fileList.get(i);
      if (file.exists()) {
        file.delete();
      }
      if (!file.exists()) {
        file.createNewFile();
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("DATA");
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), StandXmlTool.format);
        writer.setEscapeText(false);
        writer.write(document);
        writer.close();
      }
    }
  }

  public void BornXml() throws IOException, DocumentException {
    //获取所有设备的ID和value
    Map<String, String> all_device = StandXmlTool.GetCellIDAndValue("1", 1);
    //按照设备deviceID升序排序
    Map<String, String> sort_map;
    sort_map = StandXmlTool.SortDeviceMap(all_device);
    for (Map.Entry<String, String> all : sort_map.entrySet()) {
      System.out.println("当前设备为" + all.getValue());
      //获取当前设备的数据表类型和对应ID
      Map<String, String> one_device = StandXmlTool.GetCellIDAndValue(all.getKey(), 2);
      //获取单一设备的map<table_name,table_data>信息
      Map<String, String> device_map = StandXmlTool.GetDeviceMap(one_device);
      //DrawIO-table->Xml
      StandXmlTool.WriteXml(device_map);
    }
  }
  /**
   *
   * @param parentID 通过父类ID获取到cell的id和value  要拿两种东西的id，一种是纯cell。另一种是object
   * @param  F=1，ret对应XML中的device，F=2时，ret对应每一个device的各种表
   * @return
   * @throws DocumentException
   * @throws IOException
   */
  private static Map<String, String> GetCellIDAndValue(String parentID,int F) throws DocumentException, IOException {
    SAXReader reader = new SAXReader();
    Map<String, String> m1 = new LinkedHashMap<>();
    System.out.println(Config.DrawXML);
    File xml_file = new File(Config.DrawXML);
    Document document = reader.read(xml_file);

    List<Node> cells = document.selectNodes("/mxfile/diagram/mxGraphModel/root/object | /mxfile/diagram/mxGraphModel/root/mxCell");
    Iterator<Node> it = cells.iterator();
    while (it.hasNext()) {
      Element e = (Element) it.next();
      //筛选label节点
      if (F==1) {
        if (!StringUtils.isNullOrEmpty(e.attributeValue("label")) && CheckTableName(e.attributeValue("label")).length() != 0) {
          m1.put(e.attributeValue("id"), e.attributeValue("label"));
        }
        //筛选cell节点
        if (!StringUtils.isNullOrEmpty(e.attributeValue("value")) && CheckTableName(e.attributeValue("value")).length() != 0) {
          if (e.attributeValue("parent").equals(parentID)) {
            m1.put(e.attributeValue("id"), e.attributeValue("value"));
          }
        }
      }
      else if (F==2)
      {
        if (!StringUtils.isNullOrEmpty(e.attributeValue("value"))&&e.attributeValue("parent").equals(parentID)) {
            m1.put(e.attributeValue("id"), e.attributeValue("value"));
        }
      }
    }
    return m1;
  }

  /**
   * 将device_map按照device-id升序排序
   *
   * @param device_map
   * @return
   */
  public static Map<String, String> SortDeviceMap(Map<String, String> device_map) {
    //将device_map转成list进行排序
    List<Map.Entry<String, String>> list = new ArrayList<>(device_map.entrySet());
    //对list进行处理
    for (Map.Entry<String, String> s : list) {
      s.setValue(CheckTableName(s.getValue()));
      System.out.println(s.getValue());
    }
    Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
      public int compare(Map.Entry<String, String> o1,
                         Map.Entry<String, String> o2) {
        return Integer.parseInt(o1.getValue().split("-")[0]) - Integer.parseInt(o2.getValue().split("-")[0]);
      }
    });
    //将排序后的list再转成Map
    Map<String, String> sort_map = new LinkedHashMap<>();
    for (Iterator<Map.Entry<String, String>> it = list.iterator(); it.hasNext(); ) {
      Map.Entry<String, String> temp = it.next();
      if (!temp.getValue().equals("0-example"))
        sort_map.put(temp.getKey(), temp.getValue());
    }
    return sort_map;
  }

  private static Map<String, String> GetDeviceMap(Map<String, String> one_device) throws IOException, DocumentException {
    Map<String, String> device = new LinkedHashMap<>();
    List<String> list = new ArrayList<String>();
    for (Map.Entry<String, String> entry : one_device.entrySet()){
        list.add(entry.getValue());
    }
    for (int i=0;i<list.size();i+=2)
    {
      org.jsoup.nodes.Document table_data = Jsoup.parse(list.get(i+1));
      device.put(list.get(i), String.valueOf(table_data.text()));
    }
    return device;
  }

  /**
   * 获取map中第一个数据值
   *
   * @param map 数据源
   * @return
   */
  private static String getFirstOrNull(Map<String, String> map) {
    String obj = null;
    for (Map.Entry<String, String> entry : map.entrySet()) {
      obj = entry.getKey();
      if (obj != null) {
        break;
      }
    }
    return obj;
  }
  /**
   * 将device-table中的信息分类写入Xml
   *
   * @param device_map
   */
  private static void WriteXml(Map<String, String> device_map) throws IOException {

    for (Map.Entry<String, String> dev : device_map.entrySet()) {
      //Config.devTable==设备信息
      if (dev.getKey().equals(Config.devTable)) {
        if (dev.getValue().split("\\s+").length % 5 == 0) {
          String a = getTableData(dev.getValue(), 5);
          id = Integer.parseInt(a.split("\\s+")[0]);
          DeviceInfoXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.IfTable==接口信息
      if (dev.getKey().equals(Config.IfTable)) {
        if (dev.getValue().split("\\s+").length % 3 == 0) {
          String a = getTableData(dev.getValue(), 3);
          DeviceIfXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.PhyTable==物理层
      if (dev.getKey().equals(Config.PhyTable)) {
        if (dev.getValue().split("\\s+").length % 3 == 0) {
          String a = getTableData(dev.getValue(), 3);
          DevicePhyXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.RouteTable==网络层
      if (dev.getKey().equals(Config.RouteTable)) {
        if (dev.getValue().split("\\s+").length % 4 == 0) {
          String a = getTableData(dev.getValue(), 4);
          DeviceRouteXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.DfTable==数据流层
      if (dev.getKey().equals(Config.DfTable)) {
        if (dev.getValue().split("\\s+").length % 16 == 0) {
          String a = getTableData(dev.getValue(), 16);
          DeviceDataflowXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.DfTable==系统环境层
      if (dev.getKey().equals(Config.SysTable)) {
        if (dev.getValue().split("\\s+").length % 8 == 0) {
          String a = getTableData(dev.getValue(), 8);
          DeviceService_And_DeviceprivilegeXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.UserPTable==测试权限表
      if (dev.getKey().equals(Config.UserPTable)) {
        if (dev.getValue().split("\\s+").length % 3 == 0) {
          String a = getTableData(dev.getValue(), 3);
          UserprivilegeXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.DeviceSecFuncTable==设备安全功能表
      if (dev.getKey().equals(Config.DeviceSecFuncPTable)) {
        if (dev.getValue().split("\\s+").length % 2 == 0) {
          String a = getTableData(dev.getValue(), 2);
          DeviceSecurityXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.SecThreatTable==设备安全威胁表
      if (dev.getKey().equals(Config.DeviceSecThreatTable)) {
        if (dev.getValue().split("\\s+").length % 4 == 0) {
          String a = getTableData(dev.getValue(), 4);
          DeviceSThreatXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.SysSecFuncPTable==全局安全功能分类表
      if (dev.getKey().equals(Config.SysSecFuncPTable)) {
        if (dev.getValue().split("\\s+").length % 3 == 0) {
          String a = getTableData(dev.getValue(), 3);
          SysSecFuncXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.SysSecThreatTable==全局安全功能威胁映射表
      if (dev.getKey().equals(Config.SysSecThreatTable)) {
        if (dev.getValue().split("\\s+").length % 3 == 0) {
          String a = getTableData(dev.getValue(), 3);
          SysSecThreatcXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
      //Config.SysSecThreatTable==攻击模板
      if (dev.getKey().equals(Config.AttackModel)) {
        if (dev.getValue().split("\\s+").length % 4 == 0) {
          String a = getTableData(dev.getValue(), 4);
          AttackModelXml(a);
        } else {
          System.out.println(dev.getKey() + "导入有误！" + dev.getValue());
        }
      }
    }

  }



  /**
   * @param string 设备单元
   *               //table_name.text()--设备信息
   *               //table_data.text()--device_ID device_name device_tpye device_deploy device_value 2 互联网安全网关 业务设备 0 2
   * @return 返回device_map<table_name, table_data>
   */
  private static String CheckTableName(String string) {
    // pattern匹配0-安全功能威胁映射表
    String pattern = "[0-9]{1,}-[\\u4e00-\\u9fa5A-Za-z0-9]{1,}";
    //pattern匹配安全功能威胁映射表
    String pattern2 = "[\\u4e00-\\u9fa5]{1,}";
    // 创建 Pattern 对象
    Pattern r = Pattern.compile(pattern);
    Pattern r2 = Pattern.compile(pattern2);
    // 现在创建 matcher 对象
    Matcher m = r.matcher(string);
    Matcher m2 = r2.matcher(string);
    //
    if (m.find()) {
      //            System.out.println("Found value: " + m.group(0));
      return m.group(0);
    } else if (m2.find()) {
      //            System.out.println("Found value: " + m.group(0));
      return m2.group(0);
    } else {
//      System.out.println(string + "tableName有问题");
      return "";
    }

  }

  /**
   * 去掉table_data字符串前面的device_name等，只返回具体数据
   **/
  private static String getTableData(String value, int count) {
    String[] arr = value.trim().split("\\s+");

    StringBuffer a = new StringBuffer();
    for (int i = 0; i < arr.length - count; i++) {
      a.append(arr[i + count] + " ");
    }
    return a.toString();
  }

  private static void DeviceInfoXml(String string) throws IOException {
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.DeviceInfoXML);
      Document doc = reader.read(file);
      Element ROW = doc.getRootElement().addElement("ROW");
      Element device_id = ROW.addElement("device_id");
      Element device_name = ROW.addElement("device_name");
      Element device_type = ROW.addElement("device_type");
      Element device_deploy = ROW.addElement("device_deploy");
      Element device_value = ROW.addElement("device_value");
      device_id.setText(arr[0]);
      device_name.setText(arr[1]);
      device_type.setText(arr[2]);
      device_deploy.setText(arr[3]);
      device_value.setText(arr[4]);
      XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
      write.write(doc);
      write.close();
      //            System.out.println(arr[0]+"device_info已导入");
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (DocumentException ex) {
      ex.printStackTrace();
    }
  }

  private static void DeviceIfXml(String string) throws IOException {
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.DeviceNetworkXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 3; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element device_id = ROW.addElement("device_id");
        Element interface_id = ROW.addElement("interface_id");
        Element address = ROW.addElement("address");
        Element netmask = ROW.addElement("netmask");
        device_id.setText(String.valueOf(id));
        interface_id.setText(arr[3 * i]);
        address.setText(arr[3 * i + 1]);
        netmask.setText(arr[3 * i + 2]);
        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
      //            System.out.println(arr[0]+"device_phy已导入");
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (DocumentException ex) {
      ex.printStackTrace();
    }
  }

  private static void DevicePhyXml(String string) throws IOException {
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.DeviceConnectXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 3; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element device_id = ROW.addElement("device_id");
        Element interface_id = ROW.addElement("interface_id");
        Element peer_device_id = ROW.addElement("peer_device_id");
        Element peer_interface_id = ROW.addElement("peer_interface_id");
        device_id.setText(String.valueOf(id));
        interface_id.setText(arr[3 * i]);
        peer_device_id.setText(arr[3 * i + 1]);
        peer_interface_id.setText(arr[3 * i + 2]);
        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
      //            System.out.println(arr[0]+"device_phy已导入");
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (DocumentException ex) {
      ex.printStackTrace();
    }
  }

  private static void DeviceRouteXml(String string) throws IOException {
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.DeviceRouteXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 4; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element device_id = ROW.addElement("device_id");
        Element destination = ROW.addElement("destination");
        Element genmask = ROW.addElement("genmask");
        Element gateway = ROW.addElement("gateway");
        Element interface_id = ROW.addElement("interface_id");
        device_id.setText(String.valueOf(id));
        destination.setText(arr[4 * i]);
        genmask.setText(arr[4 * i + 1]);
        gateway.setText(arr[4 * i + 2]);
        interface_id.setText(arr[4 * i + 3]);
        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
      //            System.out.println(arr[0]+"device_phy已导入");
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (DocumentException ex) {
      ex.printStackTrace();
    }
  }

  private static void DeviceDataflowXml(String string) throws IOException {
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.DeviceDataflowXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 16; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element device_id = ROW.addElement("device_id");
        Element type = ROW.addElement("type");
        Element privilege_id = ROW.addElement("privilege_id");
        Element src = ROW.addElement("src");
        Element sport = ROW.addElement("sport");
        Element dst = ROW.addElement("dst");
        Element dport = ROW.addElement("dport");
        Element protocol = ROW.addElement("protocol");
        Element variant_id = ROW.addElement("variant_id");
        Element t_privilege_id = ROW.addElement("t_privilege_id");
        Element t_src = ROW.addElement("t_src");
        Element t_sport = ROW.addElement("t_sport");
        Element t_dst = ROW.addElement("t_dst");
        Element t_dport = ROW.addElement("t_dport");
        Element t_protocol = ROW.addElement("t_protocol");
        Element t_variant_id = ROW.addElement("t_variant_id");
        Element priority = ROW.addElement("priority");
        device_id.setText(String.valueOf(id));
        type.setText(arr[16 * i]);
        privilege_id.setText(arr[16 * i + 1]);
        src.setText(arr[16 * i + 2]);
        sport.setText(arr[16 * i + 3]);
        dst.setText(arr[16 * i + 4]);
        dport.setText(arr[16 * i + 5]);
        protocol.setText(arr[16 * i + 6]);
        variant_id.setText(arr[16 * i + 7]);
        t_privilege_id.setText(arr[16 * i + 8]);
        t_src.setText(arr[16 * i + 9]);
        t_sport.setText(arr[16 * i + 10]);
        t_dst.setText(arr[16 * i + 11]);
        t_dport.setText(arr[16 * i + 12]);
        t_protocol.setText(arr[16 * i + 13]);
        t_variant_id.setText(arr[16 * i + 14]);
        priority.setText(arr[16 * i + 15]);
        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
      //            System.out.println(arr[0]+"device_phy已导入");
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (DocumentException ex) {
      ex.printStackTrace();
    }
  }

  private static void DeviceService_And_DeviceprivilegeXml(String string) {
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      //生成device_service.xml
      File DeviceService = new File(Config.DeviceServiceXML);
      Document doc_DeviceService = reader.read(DeviceService);
      //提取出第一个表需要的数据
      String deviceSerV[][] = new String[arr.length/8][6];
      for (int i = 0; i < arr.length / 8; i++) {
            deviceSerV[i][0]=String.valueOf(id);
            deviceSerV[i][1]=arr[8 * i ];
            deviceSerV[i][2]=arr[8 * i + 2];
            deviceSerV[i][3]=arr[8 * i + 3];
            deviceSerV[i][4]=arr[8 * i + 4];
            deviceSerV[i][5]=arr[8 * i + 5];
      }

      String NewdeviceSerV[][]  = ReplaceRepeat(deviceSerV);
      for (int i = 0; i < NewdeviceSerV.length; i++) {
        Element DeviceService_ROW = doc_DeviceService.getRootElement().addElement("ROW");
        Element device_id = DeviceService_ROW.addElement("device_id");
        Element service_id = DeviceService_ROW.addElement("service_id");
        Element enviroment = DeviceService_ROW.addElement("enviroment");
        Element port = DeviceService_ROW.addElement("port");
        Element service = DeviceService_ROW.addElement("service");
        Element protocol = DeviceService_ROW.addElement("protocol");
        device_id.setText(NewdeviceSerV[i][0]);
        service_id.setText(NewdeviceSerV[i][1]);
        enviroment.setText(NewdeviceSerV[i][2]);
        service.setText(NewdeviceSerV[i][3]);
        port.setText(NewdeviceSerV[i][4]);
        protocol.setText(NewdeviceSerV[i][5]);

        XMLWriter write = new XMLWriter(new FileOutputStream(DeviceService), format);
        write.write(doc_DeviceService);
        write.close();
      }
      //生成device_privilege.xml
      File DeivcePrivilege = new File(Config.DeivcePrivilegeXML);
      Document doc_DeivcePrivilege = reader.read(DeivcePrivilege);
      for (int i = 0; i < arr.length / 8; i++) {
        Element DeivcePrivilege_ROW = doc_DeivcePrivilege.getRootElement().addElement("ROW");
        Element DeivcePrivilege_device_id = DeivcePrivilege_ROW.addElement("device_id");
        Element DeivcePrivilege_service_id = DeivcePrivilege_ROW.addElement("service_id");
        Element privilege_id = DeivcePrivilege_ROW.addElement("privilege_id");
        Element data = DeivcePrivilege_ROW.addElement("data");
        Element comment = DeivcePrivilege_ROW.addElement("comment");
        DeivcePrivilege_device_id.setText(String.valueOf(id));
        DeivcePrivilege_service_id.setText(arr[8 * i]);
        privilege_id.setText(arr[8 * i + 1]);
        data.setText(arr[8 * i + 6]);
        comment.setText(arr[8 * i + 7]);
        XMLWriter DeivcePrivilege_write = new XMLWriter(new FileOutputStream(DeivcePrivilege), format);
        DeivcePrivilege_write.write(doc_DeivcePrivilege);
        DeivcePrivilege_write.close();
      }


    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (DocumentException ex) {
      ex.printStackTrace();
    }
  }

  private static String[][] ReplaceRepeat(String[][] arr) {
    String temp1="";
    List<String> list = new ArrayList<String>();
    for (int i=0;i<arr.length;i++)
    {
      String temp = "";
      for (int j = 0; j < 6; j++) {
        temp = temp.concat(arr[i][j]).concat("%%");
      }
      if (!list.contains(temp))
        list.add(temp);
    }
    String ret[][] = new String[list.size()][];
    for (int i=0;i<list.size();i++,temp1=""){
      temp1 = temp1.concat(list.get(i));
      ret[i] = temp1.split("%%");
    }
    return ret;
  }
 private static void UserprivilegeXml(String string) {
    //生成user_privilege.xml
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File UserPrivilege = new File(Config.UserPrivilegeXML);
      Document doc_UserPrivilege = reader.read(UserPrivilege);
      for (int i = 0; i < arr.length / 3; i++) {
        Element UserPrivilege_ROW = doc_UserPrivilege.getRootElement().addElement("ROW");
        Element UserPrivilege_device_id = UserPrivilege_ROW.addElement("device_id");
        Element UserPrivilege_service_id = UserPrivilege_ROW.addElement("service_id");
        Element Userprivilege_id = UserPrivilege_ROW.addElement("privilege_id");
        UserPrivilege_device_id.setText(arr[3 * i]);
        UserPrivilege_service_id.setText(arr[3 * i + 1]);
        Userprivilege_id.setText(arr[3 * i + 2]);
        XMLWriter UserPrivilege_write = new XMLWriter(new FileOutputStream(UserPrivilege), format);
        UserPrivilege_write.write(doc_UserPrivilege);
        UserPrivilege_write.close();
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }

  }

  private static void DeviceSecurityXml(String string) {
    //生成device_security.xml
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.DeviceSecurityXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 2; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element device_id = ROW.addElement("device_id");
        Element function_id = ROW.addElement("function_id");
        Element owner_id = ROW.addElement("owner_id");
        device_id.setText(String.valueOf(id));
        function_id.setText(arr[2 * i]);
        owner_id.setText(arr[2 * i + 1]);

        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }

  }

  private static void DeviceSThreatXml(String string) {
    //生成device_threat.xml
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.DeviceThreatXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 4; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element device_id = ROW.addElement("device_id");
        Element service_id = ROW.addElement("service_id");
        Element privilege_id = ROW.addElement("privilege_id");
        Element threat_id = ROW.addElement("threat_id");
        Element threat_weight = ROW.addElement("threat_weight");
        device_id.setText(String.valueOf(id));
        service_id.setText(arr[4 * i]);
        privilege_id.setText(arr[4 * i + 1]);
        threat_id.setText(arr[4 * i + 2]);
        threat_weight.setText(arr[4 * i + 3]);
        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private static void SysSecFuncXml(String string) {
    //生成security_function.xml
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.SecurityFuncXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 3; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element function_id = ROW.addElement("function_id");
        Element function_name = ROW.addElement("function_name");
        Element security_class = ROW.addElement("security_class");
        function_id.setText(arr[3 * i]);
        function_name.setText(arr[3 * i + 1]);
        security_class.setText(arr[3 * i + 2]);
        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private static void SysSecThreatcXml(String string) {
    //生成security_threat.xml
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.SecurityThreatXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 3; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element threat_id = ROW.addElement("threat_id");
        Element threat_name = ROW.addElement("threat_name");
        Element function_id = ROW.addElement("function_id");
        threat_id.setText(arr[3 * i]);
        threat_name.setText(arr[3 * i + 1]);
        function_id.setText(arr[3 * i + 2]);
        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }
  private static void AttackModelXml(String string) {
    //生成security_threat.xml
    try {
      String[] arr = string.split("\\s+");
      SAXReader reader = new SAXReader();
      File file = new File(Config.AttackModelXML);
      Document doc = reader.read(file);
      for (int i = 0; i < arr.length / 3; i++) {
        Element ROW = doc.getRootElement().addElement("ROW");
        Element vuln_id = ROW.addElement("vuln_id");
        Element vuln_name = ROW.addElement("vuln_name");
        Element pre_privilege_id = ROW.addElement("pre_privilege_id");
        Element fin_privilege_id = ROW.addElement("fin_privilege_id");
        vuln_id.setText(arr[4 * i]);
        vuln_name.setText(arr[4 * i + 1]);
        pre_privilege_id.setText(arr[4 * i + 2]);
        fin_privilege_id.setText(arr[4 * i + 3]);
        XMLWriter write = new XMLWriter(new FileOutputStream(file), format);
        write.write(doc);
        write.close();
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }
}

