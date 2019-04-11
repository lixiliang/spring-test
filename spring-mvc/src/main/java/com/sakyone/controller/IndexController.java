package com.sakyone.controller;

import com.sakyone.interceptor.ControllerLogInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lixiliang on 2018/12/29.
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/home")
    @ResponseBody
    public String index(){
        logger.info("visitor home");
        return "home";
    }

    @RequestMapping("/des")
    @ResponseBody
    public String des(){
        logger.info("visitor des");
        return "des";
    }

    @RequestMapping("/a/{serial}")
    @ResponseBody
    public String urlpath(@PathVariable("serial") String serial){
        logger.info("visitor urlpath:"+serial);
        return "urlpath";
    }
}
