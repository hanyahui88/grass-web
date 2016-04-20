package com.grass.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public void index(){
        System.err.println("init index");
    }
}
