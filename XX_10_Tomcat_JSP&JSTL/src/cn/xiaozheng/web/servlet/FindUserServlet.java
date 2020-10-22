package cn.xiaozheng.web.servlet;

import cn.xiaozheng.domain.User;
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
 * @CreateTime: 2020/7/16 1:27
 * @Description: 信息回显
 */
@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取id
        String id = request.getParameter("id");
        //2.调用Service查找单一数据
        UserService service = new USerServiceImpl();
        User user =  service.findUserById(id);
        //3.将user存入request
        request.setAttribute("user",user);
        //4.跳转UserListServlet
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
