package cn.web.request;

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
 * @CreateTime: 2020/7/8 20:35
 * @Description: 获取请求头数据：referer  需要从网页链接点击才可以获取请求  意为：我从哪里来
 */
@WebServlet("/ServletRequest_2")
public class ServletRequest_2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");//通过请求头的名字获取值
        System.out.println(referer);
        //结果：http://localhost:8080/XX_7_Tomcat_Servl_war_exploded/login.html
        //防盗链
        if (referer != null) {
            if (referer.contains("/XX_7_Tomcat_Servlet_war_exploded")) {
                //正常访问
                //System.out.println("播放电影");
                response.setContentType("text/html;charset=utf-8");
                //把文字显示在网页上
                response.getWriter().write("播放电影");
            }else{
                //System.out.println("想看电影吗？来正规网站哦！哦");
                response.setContentType("text/html;charset=utf-8");
                //把文字显示在网页上
                response.getWriter().write("看电影吗？来正规网站哦！哦");
            }
        }
    }
}
