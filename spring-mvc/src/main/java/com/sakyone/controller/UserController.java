package com.sakyone.controller;

import com.alibaba.fastjson.JSON;
import com.sakyone.entity.GrayUser;
import com.sakyone.service.UserService;
import com.sakyone.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
   @RequestMapping("/index.html")
   public ModelAndView getIndex(HttpServletRequest request) throws Exception {
       ModelAndView mav = new ModelAndView("index");
       return mav;
   }

   //跳转到登录页面
   @RequestMapping("/login.html")
   public ModelAndView login() throws Exception {
       ModelAndView mav = new ModelAndView("login");
       return mav;
   }

   //跳转到登录成功页面
   @RequestMapping("/loginsuccess.html")
   public ModelAndView loginsuccess() throws Exception {
       Subject currentUser = SecurityUtils.getSubject();
       String userName = (String)currentUser.getPrincipal();
       ModelAndView mav = new ModelAndView("loginsuccess");
       mav.getModel().put("userName",userName);
       return mav;
   }

     /**
    * 验证用户名和密码 
    * @param  username,password
    * @return 
    */  
   @RequestMapping(value="/checkLogin.json",method=RequestMethod.POST)  
   @ResponseBody  
   public String checkLogin(String username,String password) throws Exception {
       Map<String, Object> result = new HashMap<String, Object>();
       try{
           UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.MD5(password));
           Subject currentUser = SecurityUtils.getSubject();  
           if (!currentUser.isAuthenticated()){
               //使用shiro来验证  
               token.setRememberMe(true);  
               currentUser.login(token);//验证角色和权限  
           } 
       }catch(Exception ex){
           logger.error("exception,msg:",ex);
           throw new Exception("exception,msg:"+ex.getMessage());
       }
       result.put("success", true);
       return JSON.toJSONString(result);
   }  

   /** 
    * 退出登录
    */  
   @RequestMapping(value="/logout.json")
   @ResponseBody    
   public String logout() {   
       Map<String, Object> result = new HashMap<String, Object>();
       result.put("success", true);
       Subject currentUser = SecurityUtils.getSubject();       
       currentUser.logout();    
       return JSON.toJSONString(result);
   }

    @RequestMapping(value="/user/{id}")
    @ResponseBody
    public String user(@PathVariable("id") Long id) {
        System.out.println("id:"+id);
        GrayUser user = userService.findUserById(id);
        return JSON.toJSONString(user);
    }
}
