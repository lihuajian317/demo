package com.lihuajian.configer;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration //将此Filter交给Spring容器管理
@Order(1) //指定过滤器的执行顺序,值越大越靠后执行
@WebFilter(filterName = "securityFilter", urlPatterns = "/*")
public class FilterConfiger implements Filter {

    private String url = "login,index,session";

    private String urlArray[];

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("getFilterName:" + filterConfig.getFilterName());//返回<filter-name>元素的设置值。
        System.out.println("getServletContext:" + filterConfig.getServletContext());//返回FilterConfig对象中所包装的ServletContext对象的引用。
        System.out.println("getInitParameter:" + filterConfig.getInitParameter("cacheTimeout"));//用于返回在web.xml文件中为Filter所设置的某个名称的初始化的参数值
        System.out.println("getInitParameterNames:" + filterConfig.getInitParameterNames());//返回一个Enumeration集合对象。

        urlArray = url.split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getRequestURI();//获取请求URL
        boolean flag = false;
        //判断请求URL是否包含指定后缀
        for (int i = 0; i < urlArray.length; i++) {
            if (requestUrl.endsWith(urlArray[i])) {
                flag = true;
                break;
            }
        }
        /*if (!flag) {
            //不包含指定后缀

            //判断用户是否登陆
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                //判断用户请求是否是ajax请求
                if (isAjaxRequest(request)) {
                    response.setHeader("sessionstatus", "timeout");
                } else {
                    System.out.println(request.getContextPath());
                    response.sendRedirect(request.getContextPath()+"index/tologin");
                }
                return ;
            }
        }*/
        //包含指定后缀
        filterChain.doFilter(servletRequest, servletResponse);//doFilter将请求转发给过滤器链下一个filter , 如果没有filter那就是你请求的资源
    }

    @Override
    public void destroy() {

    }

    private static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return header != null && header.equalsIgnoreCase("XMLHttpRequest");
    }
}
