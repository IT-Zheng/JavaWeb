package cn.xiaozheng.web.servlet;

import cn.xiaozheng.domain.Login;
import cn.xiaozheng.service.UserService;
import cn.xiaozheng.service.impl.USerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/13 19:23
 * @Description: 登录
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置request编码
        request.setCharacterEncoding("utf-8");
        //获取用户验证码
        String checkCode = request.getParameter("verifycode");
        //3.判断验证码值是否正确
            //获取生成的验证码
        HttpSession session = request.getSession();//先获取session对象
        String ses = (String) session.getAttribute("checkCode_session");
            //删除验证码
        session.removeAttribute("checkCode_session");
        if (!checkCode.equalsIgnoreCase(ses)) {
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg","验证码错误!!!");
            //跳转页面
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        //2.获取用户填写的所有参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //4封装Login对象
        Login login = new Login();
        try {
            BeanUtils.populate(login,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //5.调用Service完成查询
        UserService Service = new USerServiceImpl();
        Login log = Service.login(login);
        System.out.println(log);
        //6.判断是否登录成功
        if (log != null){
            //登录成功
            //将用户存入session
            session.setAttribute("username", log);
            //重定向转发
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误!!!");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
