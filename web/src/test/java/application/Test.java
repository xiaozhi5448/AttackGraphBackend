package application;

import application.graph.Graph;
import application.graph.GraphData;
import com.alibaba.fastjson.JSONObject;
import com.loop0day.security.db.bean.DeviceRoute;
import com.loop0day.security.db.bean.DeviceRouteExample;
import com.loop0day.security.db.dao.DeviceRouteMapper;
import com.loop0day.security.tool.MapTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.loop0day.security.core.SecuritySystem;
import java.util.List;

import org.junit.jupiter.api.Test;

@SpringBootTest
class DemoTest {

    @Autowired
    MapTool mapTool;
    @Value("${rest.plot-graph-addr}")
    String plotAddr;

    Logger logger = LoggerFactory.getLogger(DemoTest.class);

    @Test
    public void testMybatis() {
        DeviceRouteMapper drmapper = mapTool.session.getMapper(DeviceRouteMapper.class);

        DeviceRouteExample example = new DeviceRouteExample();
        example.or().andDestinationEqualTo("0.0.0.0");
        List<DeviceRoute> routes = drmapper.selectByExample(example);
        logger.info(routes.toString());
        String count = Long.toString(drmapper.countByExample(example));
        logger.info(count);

    }

    @Test
    public void testGraph() throws Exception {

        logger.info(System.getProperty("user.dir"));
        SecuritySystem system = new SecuritySystem();
        system.importXMLData();
        system.buildAttackPath();
        GraphData graph = new GraphData();
        graph.calculateGraphData("外网计算机");
        Graph realGraph = graph.getAttackGraph();
        System.out.println(realGraph.toString());
        logger.info(String.format("node size: %d; edge size: %d;", realGraph.getNodes().size(), realGraph.getEdges().size()));
        String jsonString = JSONObject.toJSONString(realGraph);
        logger.info(jsonString);
        graph.clearAll();
    }

    @Test
    public void testConfig() {
        logger.info(plotAddr);
    }


}
