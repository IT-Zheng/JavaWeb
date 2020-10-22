package cn.Web.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/13 19:23
 * @Description:
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置request编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数Map
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        //3.获取Session的验证码
        HttpSession session = request.getSession();
        String sc = (String) session.getAttribute("checkCode_session");
        //删除验证码
        session.removeAttribute("checkCode_session");
        //4.判断验证码值是否正确
        if (checkCode.equalsIgnoreCase(sc)) {
            //忽略大小写，判断用户名密码是否一致
            if ("zhangsan".equals(username) && "123".equals(password)){
                //储存信息
                session.setAttribute("user",username);
                //登录成功,冲定向
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }else {
                //登录失败
                request.setAttribute("login_error","用户名或密码错误");
                //重定向
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else {
            //验证码不一样，存贮信息到request
            request.setAttribute("yzm","验证码错误");
            //转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
