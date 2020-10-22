package cn.xiaozheng.travel.web.servlet;

import cn.xiaozheng.travel.domain.ResultInfo;
import cn.xiaozheng.travel.domain.User;
import cn.xiaozheng.travel.service.UserService;
import cn.xiaozheng.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/6 8:57
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private static final String Y = "Y";
    private final UserService userService = new UserServiceImpl();

    private User user(Map<String, String[]> map){
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 用户注册方法
     * 1.验证码校验
     * 2.用户注册，判断注册情况/是否有用户名重复
     * 3.将注册结果信息返回页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //封装后端返回前端的数据对象
        ResultInfo info = new ResultInfo();
        //将对象序列化为json的类
        ObjectMapper mapper = new ObjectMapper();
        /*
         * 验证码校验
         */
        //1.从页面获取验证码输入内容
        String check = request.getParameter("check");

        //2.从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        //2.1删除session
        session.removeAttribute("CHECKCODE_SERVER");

        //3.比较页面输入的验证码和后台的验证码
        if (checkcodeServer == null || !checkcodeServer.equalsIgnoreCase(check)) {
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            //将info对象序列为json
            String json = mapper.writeValueAsString(info);

            //将json数据写回客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        /*用户注册*/
        boolean flag = userService.regist(user(request.getParameterMap()));

        //4.响应结果
        if (flag) {
            //注册成功
            info.setFlag(true);
            info.setErrorMsg("注册成功！");
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！用户名重复！");
        }

        //5.将info对象序列为json
        String json = mapper.writeValueAsString(info);

        //6.将json数据写回客户端
        //6.1设置
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 用户登录方法
     * 1.判断用户是否为null
     * 2.判断用户是否激活
     * 3.将失败数据信息返回到页面/成功跳转页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //3.调用service查询
        User u = userService.login(user(request.getParameterMap()));

        ResultInfo info = new ResultInfo();
        //4.判断用户是否为null
        if (u == null) {
            //用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //5.判断用户是否激活
        if (u != null && !Y.equals(u.getStatus())) {
            //用户未激活
            info.setFlag(false);
            info.setErrorMsg("您未激活，请激活");
        }
        //6.判断登录成功
        if (u != null && Y.equals(u.getStatus())) {
            //登陆成功
            request.getSession().setAttribute("user",u);
            info.setFlag(true);
        }

        //7.响应数据

        writeValue(info,response);
    }

    /**
     * 用户登录
     * 将用户登录后的 session中的数据返回到页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写会客户端

        writeValue(user,response);
    }

    /**
     * 用户退出方法
     * 1.销毁session
     * 2.跳转页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();
        //2.跳转页面到登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 用户注册邮箱激活验证方法
     * 1.获取唯一标识code
     * 2.判断是否激活成功
     * 3.跳转页面/返回失败信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            //2.调用service完成注册
            boolean flag = userService.active(code);
            //3.判断标记,跳转不同的页面
            String msg;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href = 'login.html'>登录</a>";
            } else {
                //激活失败
                msg = "激活成功，请重新激活，或联系管理员！</a>";
            }
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(msg);
        }
    }
}
