package cn.web.Servlet;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @Package: cn.web.Servlet
 * @ClassName: ServletDemo
 * @Author: Bad Body
 * @CreateTime: 2020/7/8 14:23
 * @Description:
 */
//@WebServlet要导入servlet包下的：import javax.servlet.annotation.WebServlet;
@WebServlet(urlPatterns = "/demo")//一个值可以不带urlPattenrns或value，直接"资源路径"
public class ServletDemo implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servlet 4.0 来了!!!!!!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
