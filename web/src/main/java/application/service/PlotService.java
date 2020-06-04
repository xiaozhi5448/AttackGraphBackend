package application.service;

import application.bean.ImagePath;
import application.graph.Graph;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PlotService {
    Logger logger = LoggerFactory.getLogger(PlotService.class);
    private RestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    public PlotService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public String plotGraph(String url, Graph graph){
        String graphData = JSONObject.toJSONString(graph);
        logger.info(graphData);
        HttpEntity<String> entity = new HttpEntity<>(graphData, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(url, entity, String.class);
        if(result.getStatusCode() == HttpStatus.BAD_REQUEST){
            logger.info("error occured!");
            return "plot backend service error";
        }
        logger.info(result.getBody());
        return result.getBody();
    }
}
