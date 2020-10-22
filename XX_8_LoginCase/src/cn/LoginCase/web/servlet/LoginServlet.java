package cn.LoginCase.web.servlet;

import cn.LoginCase.dao.UserDao;
import cn.LoginCase.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/9 17:16
 * @Description： 2020年7月10日13:23:30 添加commons-beanutils-1.8.0.jar包  BeanUtils工具类，简化数据封装
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
//        //2.获取请求参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        //3封装对象
//        User loginUser = new User();
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);
        //获取所有请求参数
        Map<String, String[]> map = req.getParameterMap();
        //创建USer对象
        User loginUser = new User();
        //使用BeanUtils封装
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(loginUser+"-------------");
        //4.调用UserDao的login方法
        UserDao userDao = new UserDao();
        User user = userDao.login( loginUser);//把LoginUser传进去
        System.out.println(user+" UserDao 返回");
        //判断user
        if (user == null) {
            //登录失败
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else{
            //登录成功
            //存储数据
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
//        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
//        req.setCharacterEncoding("utf-8");
//        //2.获取请求参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        //3封装对象
//        User LoginUser = new User();
//        LoginUser.setUsername(username);
//        LoginUser.setPassword(password);
//        //4.调用UserDao的login方法
//        UserDao userDao = new UserDao();
//        User user = userDao.login(LoginUser);//把LoginUser传进去
//        //判断user
//        if (user == null) {
//            //登录失败
//            req.getRequestDispatcher("/failServlet").forward(req,resp);
//        }else{
//            //登录成功
//            //存储数据
//            req.setAttribute("user",user);
//            req.getRequestDispatcher("/successServlet").forward(req,resp);
//        }
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        this.doPost(request,response);
//    }
}
