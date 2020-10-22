package cn.Web.Servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/11 21:02
 * @Description: 完成重定向
 */
@WebServlet("/ServletResponse_0")
public class ServletResponse_0 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/ServletResponse_0被访问");
        //获取流之前，设置编码，流的默认编码为ISO-8859-1
        //response.setCharacterEncoding("GBK");
        //告诉浏览器数据流的编码，使其浏览器更改编码解析
        //response.setHeader("content-type","text/html,charset=utf-8");
        //简单形式的设置流的编码↓
        response.setContentType("text/html,charset=utf-8");

        //获取字符输出流
        //PrintWriter pw = response.getWriter();
        //输入数据
        //pw.write("hello response");
        //------------------------------------------------------------------------
        //获取字节输出流
        ServletOutputStream sos = response.getOutputStream();
        sos.write("hello".getBytes());
        //------------------------------------------------------------------------
        //访问/ServletResponse_0，会自动跳转到/ServletResponse_1资源
        //1.设置状态码
        //response.setStatus(302);
        //2.设置响应头
//        response.setHeader("location","/XX_9_Tomcat_Servlet_war_exploded/ServletResponse_1");

        //简单的重定向
        //response.sendRedirect("/XX_9_Tomcat_Servlet_war_exploded/ServletResponse_1");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
