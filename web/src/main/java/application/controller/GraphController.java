package application.controller;

import application.bean.ImagePath;
import application.graph.Graph;
import application.graph.GraphData;
import application.service.PlotService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loop0day.security.core.SecuritySystem;
import com.loop0day.security.db.bean.*;
import com.loop0day.security.db.dao.AttackPathMapper;
import com.loop0day.security.db.dao.DeviceMapper;
import com.loop0day.security.db.dao.DeviceNetworkMapper;
import com.loop0day.security.tool.MapTool;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/graph")
@CrossOrigin
public class GraphController {
    @Autowired
    PlotService plotService;
    @Autowired
    SecuritySystem securitySystem;
    public GraphController() {

    }
    @Autowired
    MapTool mapTool;
    @Value("${rest.plot-graph-addr}")
    String plotAddr;
    @Value("${rest.plot-host-name}")
    String plotHost;
    @Value("${rest.plot-host-port}")
    String plotPort;
    Logger logger = LoggerFactory.getLogger(GraphController.class);
    @Getter
    GraphData graphData = new GraphData();


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public String uploadFile(@RequestParam("file") MultipartFile file)throws Exception{
        if(file.isEmpty()){
            logger.error("empty file upload not allow!");
            return "error";
        }else{
//            String filename = file.getOriginalFilename();
            String path = "F:\\code\\AttackChina-drawIO-v1.xml";
            File dest = new File(path);
            try {
                file.transferTo(dest);
                logger.info(String.format("上传成功：%s", dest));

            }catch (IOException  e) {
                logger.error(e.toString(), e);
                logger.info("保存文件出错");
                return "error";
            }
            graphData.clearAll();
            securitySystem.importXMLData();
            securitySystem.buildAttackPath();
            AttackPathExample attackPathExample = new AttackPathExample();
            AttackPathMapper attackPathMapper = mapTool.session.getMapper(AttackPathMapper.class);
            attackPathExample.or().andSrcIsNotNull();
            List<AttackPath> attackPaths = attackPathMapper.selectByExample(attackPathExample);
            attackPathExample.clear();

            DeviceNetworkExample deviceNetworkExample = new DeviceNetworkExample();
            DeviceNetworkMapper deviceNetworkMapper = mapTool.session.getMapper(DeviceNetworkMapper.class);

            DeviceExample deviceExample = new DeviceExample();
            DeviceMapper deviceMapper = mapTool.session.getMapper(DeviceMapper.class);

            HashSet<String> starts = new HashSet<>();
            for(AttackPath looppath: attackPaths){
                deviceNetworkExample.or().andAddressEqualTo(looppath.getSrc());
                List<DeviceNetwork> deviceNetworks = deviceNetworkMapper.selectByExample(deviceNetworkExample);
                deviceNetworkExample.clear();
                for(DeviceNetwork device_network: deviceNetworks){
                    deviceExample.or().andDeviceIdEqualTo(device_network.getDeviceId());
                    List<Device> devices = deviceMapper.selectByExample(deviceExample);
                    deviceExample.clear();
                    starts.add(devices.get(0).getDeviceName());
                }
            }
            return JSONObject.toJSONString(starts);

        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String getGraph(HttpServletRequest request) {
        String startDeviceName = request.getParameter("startDeviceName");
        logger.info("request received with start deviceName: " + startDeviceName);
        graphData.getAttackGraph().clear();
        graphData.calculateGraphData(startDeviceName);

        String result = plotService.plotGraph(plotAddr, graphData.getAttackGraph());
        ImagePath imagePath = JSON.parseObject(result, ImagePath.class);
        String imageUri = String.format("http://%s:%s/%s", plotHost, plotPort, imagePath.getFigPath());
        return imageUri;
    }
}
