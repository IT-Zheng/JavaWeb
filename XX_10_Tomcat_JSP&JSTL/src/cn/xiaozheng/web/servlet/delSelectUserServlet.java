package cn.xiaozheng.web.servlet;

import cn.xiaozheng.service.UserService;
import cn.xiaozheng.service.impl.USerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: cn.xiaozheng.web.servlet
 * @ClassName: delSelectUserServlet
 * @Author: Bad Body
 * @CreateTime: 2020/7/16 14:18
 * @Description:
 */
@WebServlet("/delSelectUserServlet")
public class delSelectUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取所有id数组
        String[] userId = req.getParameterValues("UserId");
        //2.调用service批量删除
        UserService service = new USerServiceImpl();
        service.delsUser(userId);
        //3.跳转查询所有Servilet
        resp.sendRedirect(req.getContextPath()+"/FindUserBypageServlet?currentpage=1&rows=5");// /UserListServlet查询全部
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
