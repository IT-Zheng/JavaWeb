package cn.Web.Servlet;

import javax.servlet.ServletException;
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
 * @Description:
 */
@WebServlet("/ServletContext")
public class ServletContext extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过requset对象获取
        //javax.servlet.ServletContext servletContext = request.getServletContext();
        //通过HttpServlet获取
        javax.servlet.ServletContext servletContext1 = this.getServletContext();
        //System.out.println(servletContext);
        System.out.println(servletContext1);
            //定义文件名称
        String filename = "a.jpg";
            //获取MIME类型
        String type = servletContext1.getMimeType(filename);
        System.out.println(type);

        //System.out.println(servletContext == servletContext1);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
