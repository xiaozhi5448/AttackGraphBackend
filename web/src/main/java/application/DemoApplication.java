package application;

import application.graph.GraphData;
import com.loop0day.security.core.SecuritySystem;
import com.loop0day.security.tool.MapTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author linjian
 * @date 2019/1/15
 */
@SpringBootApplication()
public class DemoApplication {

    @Bean
    MapTool getMapTool(){
        return new MapTool();
    }

    @Bean
    SecuritySystem getSecuritySystem(){
        return new SecuritySystem();
    }
//    @Bean
//    GraphData getGraphDataTool(){
//        return new GraphData();
//    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}