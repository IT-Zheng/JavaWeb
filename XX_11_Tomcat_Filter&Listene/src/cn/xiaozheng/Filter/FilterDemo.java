package cn.xiaozheng.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Package: cn.xiaozheng.Filter
 * @ClassName: FilterDemo
 * @Author: Bad Body
 * @CreateTime: 2020/7/19 22:50
 * @Description: Filter入门
 */
//@WebFilter("/*")//访问所有资源之前，都要执行该过滤器
public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter被执行了!!!!!!!!!!!!!!!!!!!");

        //放行资源
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
