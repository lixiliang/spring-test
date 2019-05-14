package com.sakyone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lixiliang on 2018/12/29.
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        logger.info("visitor index");
        return "index";
    }

    @RequestMapping("/index")
    public String index_1(){
        logger.info("visitor index_1");
        return "index";
    }

    @RequestMapping("/index/ajax")
    @ResponseBody
    public String index_2(HttpServletRequest request){
        String url = request.getHeader("referer");
        logger.info("visitor url:{}",url);
        return "index";
    }

    @RequestMapping("/home")
    @ResponseBody
    public String home(){
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
