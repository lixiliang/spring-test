package com.sakyone.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller 请求拦截器，用于日志记录
 * 
 * @author Ternence
 *
 */
public class Interceptor_2 extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(Interceptor_2.class);

    /**
     * This implementation always returns <code>true</code>.
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("doing...");
        return true;
    }
}
