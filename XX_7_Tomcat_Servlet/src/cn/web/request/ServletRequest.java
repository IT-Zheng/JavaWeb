package cn.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: cn.web.request
 * @ClassName: ServletRequest
 * @Author: Bad Body
 * @CreateTime: 2020/7/8 18:17
 * @Description: Request对象获取请求行
 */
@WebServlet("/ServletRequest")
public class ServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求方式：GET
        String method = req.getMethod();
        System.out.println("method = " + method);
        //2.获取虚拟按目录：/XX_7_Tomcat_Servl_war_exploded
        String contextPath = req.getContextPath();
        System.out.println("contextPath = " + contextPath);
        //3.获取Servlet路径：/ServletRequest
        String servletPath = req.getServletPath();
        System.out.println("servletPath = " + servletPath);
        //4.获取get方法请求参数：name=zhangsan
        String queryString = req.getQueryString();
        System.out.println("queryString = " + queryString);
        //5.获取URI：/XX_7_Tomcat_Servl_war_exploded/ServletRequest  http://localhost:8080/XX_7_Tomcat_Servl_war_exploded/ServletRequest
        String reqRequestURI = req.getRequestURI();
        StringBuffer reqRequestURL = req.getRequestURL();
        System.out.println("reqRequestURI = " + reqRequestURI);
        System.out.println("requestURL = " + reqRequestURL);
        //6.获取协议及版本：HTTP/1.1
        String protocol = req.getProtocol();
        System.out.println("protocol = " + protocol);
        //7.获取客户机的ip地址：0:0:0:0:0:0:0:1
        String remoteAddr = req.getRemoteAddr();
        System.out.println("remoteAddr = " + remoteAddr);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
