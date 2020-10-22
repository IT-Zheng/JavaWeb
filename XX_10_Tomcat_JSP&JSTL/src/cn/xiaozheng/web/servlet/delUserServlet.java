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
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/16 0:18
 * @Description: 删除
 */
@WebServlet("/delUserServlet")
public class delUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");
        //2.调用Service保存
        UserService service = new USerServiceImpl();
        service.delUser(id);
        //3.跳转UserListServlet
        response.sendRedirect(request.getContextPath()+"/FindUserBypageServlet?currentpage=1&rows=5");// /UserListServlet查询全部
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
