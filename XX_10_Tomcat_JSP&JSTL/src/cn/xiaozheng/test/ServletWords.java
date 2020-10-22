package cn.xiaozheng.test;

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
 * @CreateTime: 2020/7/21 13:05
 * @Description:
 */
@WebServlet("/ServletWords")
public class ServletWords extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String smg = request.getParameter("smg");
        System.out.println(name+":"+smg);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
