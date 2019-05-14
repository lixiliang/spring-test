package com.sakyone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieMgrController {

    @RequestMapping(value = "/cookie", method = {RequestMethod.GET})
    public String cookie(HttpServletRequest request, Model model) {
//        model.addAttribute("token", TokenUtils.decode(request));
        System.out.println("uri: "+request.getRequestURI());
        System.out.println("url: "+request.getRequestURL());
        System.out.println("query string: "+request.getQueryString());
        System.out.println("scheme: "+request.getScheme());
        System.out.println("scheme-x-forwarde-proto: "+request.getHeader("X-Forwarded-Proto"));

        return "cookie";
    }
    @RequestMapping(value = "/cookie", method = {RequestMethod.POST})
    public String setCookie(ModelAndView view , @RequestParam String cookie, HttpServletResponse response) {
//        TokenUtils.writeTokenCookie(response, cookie);
//        view.setViewName("redirect:/cookie");
        System.out.println("setcookie post");
/*        view.setView(new RedirectView("/cookie", true));
        return view;*/
        return "cookie";
    }

    @RequestMapping(value = "/post", method = {RequestMethod.GET})
    public String post(HttpServletRequest request, Model model) {
        System.out.println("post page in");
        return "post";
    }

    @RequestMapping(value = "/postData", method = {RequestMethod.POST})
    @ResponseBody
    public String postData(@RequestParam String postData, HttpServletRequest request, HttpServletResponse response) {
        //        TokenUtils.writeTokenCookie(response, cookie);
        System.out.println("postData method:"+postData);
//        String stoken = TokenUtils.read(request);
        return "stable server response stoken:";
//        return "post";
    }

    @RequestMapping(value = "/appid/{appid}.html")
    @ResponseBody
    public String appid(@PathVariable String appid, HttpServletRequest request, HttpServletResponse response) {
        //        TokenUtils.writeTokenCookie(response, cookie);
        String stoken = "test";
        System.out.println("appid method:"+appid +" stoken:"+stoken);
        return "stable server response:"+appid +" stoken:"+stoken;
    }

    @RequestMapping(value = "/url")
    @ResponseBody
    public String url(HttpServletRequest request) {
        String stoken = "test";
        System.out.println("stable url come in");
        return "stable server response  stoken:"+stoken;
    }
    @RequestMapping(value = "/url/shopserial/{shopSerial}/branchid/{branchId}", method = {RequestMethod.GET})
    @ResponseBody
    public String url(@PathVariable String shopSerial , HttpServletRequest request, @PathVariable String branchId) {
        String stoken = "test";
        return "stable server response,shopSerial:"+shopSerial +"branchId:"+branchId + " stoken:"+stoken;
    }
    @RequestMapping(value = "/mv/{sth}", method = {RequestMethod.GET})
    @ResponseBody
    public String martrix(/*@PathVariable String mvid,*/
                          @MatrixVariable(required=false) String shopSerial ,
                          HttpServletRequest request,
                          @MatrixVariable(required=false) String branchId) {
//        System.out.println("mvid: "+mvid);
        System.out.println("shopSerial: "+shopSerial);
        System.out.println("branchId: " +branchId);
        return "stable server response,shopSerial:"+shopSerial +"branchId:"+branchId ;
    }

}
