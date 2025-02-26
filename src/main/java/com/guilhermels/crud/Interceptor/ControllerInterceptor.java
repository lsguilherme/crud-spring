package com.guilhermels.crud.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class ControllerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        log.info("[{}] Request receive: Método: {} URI: {} Status: {}", requestId, request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String requestId = MDC.get("requestId");
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("[{}] Request receive: Método: {} URI: {} Status: {} Execution time: {} ms", requestId, request.getMethod(), request.getRequestURI(), request.getRemoteAddr(), executionTime);
    }
}
