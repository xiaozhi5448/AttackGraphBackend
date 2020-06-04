package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.loop0day.security.db.bean.*;
import com.loop0day.security.db.dao.*;
import com.loop0day.security.tool.*;

import java.util.List;

@RestController
public class HelloController {
//    List<Device> ds  = MapTool.selectNetworkDevice();


    @RequestMapping("/hello")

    public String hello() {

        return "Hello world";
    }
}
