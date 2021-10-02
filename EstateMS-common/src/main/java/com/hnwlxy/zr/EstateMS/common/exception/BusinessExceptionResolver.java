package com.hnwlxy.zr.EstateMS.common.exception;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BusinessExceptionResolver extends SimpleMappingExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView mav = new ModelAndView("error/error");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("error", ex.getMessage());

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
