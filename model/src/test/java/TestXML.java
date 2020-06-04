import com.xml.Tool.StandXmlTool;
import com.xml.Tool.Xml_Tool;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.SQLException;

public class TestXML {

  public static void main(String[] args) throws IOException, SQLException, DocumentException {
    //将draw.io导出的xml转成标准格式的xml
    StandXmlTool drawio = new StandXmlTool();
    StandXmlTool.CreateXml();
    drawio.BornXml();
    //将标准格式的xml导入数据库，此模块与测试工具系统标准接口对接
    Xml_Tool.XmlWrite();
  }
}
