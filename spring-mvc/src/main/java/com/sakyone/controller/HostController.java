package com.sakyone.controller;

import com.alibaba.fastjson.JSON;
import com.sakyone.entity.GrayHost;
import com.sakyone.service.HostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/host")
public class HostController {
    private static final Logger logger = LoggerFactory.getLogger(HostController.class);
    @Autowired
    private HostService hostService;


    @RequestMapping(value="/{id}")
    @ResponseBody
    public String host(@PathVariable("id") Long id) {
        System.out.println("id:"+id);
        GrayHost host = hostService.findHostById(id);
        return JSON.toJSONString(host);
    }
}
