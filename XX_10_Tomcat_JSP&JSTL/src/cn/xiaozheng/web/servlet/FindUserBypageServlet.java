package cn.xiaozheng.web.servlet;

import cn.xiaozheng.domain.PageBean;
import cn.xiaozheng.domain.User;
import cn.xiaozheng.service.UserService;
import cn.xiaozheng.service.impl.USerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/16 16:25
 * @Description:
 */
@WebServlet("/FindUserBypageServlet")
public class FindUserBypageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentpage = request.getParameter("currentpage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数
        //优化参数
        if(currentpage == null || "".equals(currentpage)){
            currentpage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        //获取查询条件的参数
        Map<String, String[]> condition = request.getParameterMap();

        //2.调用service查询
        UserService service = new USerServiceImpl();
        PageBean<User> pB = service.findUserByPage(currentpage,rows,condition);//新增conditio
        //System.out.println(pB);
        //3.将PageBean存入request
        request.setAttribute("pB",pB);
        request.setAttribute("condition",condition);
        //4.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
