package com.sakyone.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

/**
 * Controller 请求拦截器，用于日志记录
 * 
 * @author Ternence
 *
 */
public class ControllerLogInterceptor extends HandlerInterceptorAdapter {

    private static final String REQUEST_TIME = "__request_time";
    private static final Logger logger = LoggerFactory.getLogger(ControllerLogInterceptor.class);

    /**
     * This implementation always returns <code>true</code>.
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (logger.isDebugEnabled()) {
                logger.debug("Request URI：[" + request.getRequestURI() + "]; Request Method：" + handlerMethod.getMethod().getDeclaringClass() + "." + handlerMethod
                                .getMethod().getName() + "(...)");
                logger.debug("Request params:" + JSON.toJSONString(request.getParameterMap()));
                request.setAttribute(REQUEST_TIME, new Date());
            }
            logger.info("doing...");
            return true;
        }else {
            request.setAttribute(REQUEST_TIME, new Date());
            return true;
        }
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && logger.isDebugEnabled()) {
            logger.debug("Return view ：" + modelAndView.getViewName());
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (logger.isDebugEnabled()) {
            Date requestTime = (Date) request.getAttribute(REQUEST_TIME);
            Date now = new Date();
            long costTime = now.getTime() - requestTime.getTime();
            logger.debug("Cost Time ：" + costTime + " ms,URI:" + request.getRequestURI());
        }
    }
}
