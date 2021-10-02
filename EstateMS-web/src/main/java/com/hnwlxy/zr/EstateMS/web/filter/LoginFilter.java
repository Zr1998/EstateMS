package com.hnwlxy.zr.EstateMS.web.filter;

/**
 * @ClassName LoginFilter
 * @author: hp
 * @date: 2021/5/19 17:32
 * @Version 1.0
 **/

import com.hnwlxy.zr.EstateMS.common.contants.BaseContants;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.web.aop.OperationAspect;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

@WebFilter(urlPatterns = "*.html", filterName = "loginFilter")
public class LoginFilter implements Filter {
    Logger log=Logger.getLogger(OperationAspect.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain Chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri=((HttpServletRequest) request).getRequestURI();


        if(uri!=null && !"/login.html".equals(uri) && !"/registered.html".equals(uri)){ //如果不是登录页面则进行拦截
            //判断是否登录
            Object user=((HttpServletRequest) request).getSession().getAttribute(BaseContants.LOGIN_USER);
            if(user==null){ //说明未登录
              // String msg = URLEncoder.encode("请先登录","utf-8");
                response.setCharacterEncoding("utf-8");
                response.getWriter().print("<script>alert('请先登录！');</script>");
                response.sendRedirect("/login.html");
                return;
            }
        }
        System.out.println("**********"+uri);
        Chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }


}

