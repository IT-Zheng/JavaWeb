package cn.xiaozheng.web.servlet;

import cn.xiaozheng.domain.User;
import cn.xiaozheng.service.UserService;
import cn.xiaozheng.service.impl.USerServiceImpl;
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
 * @CreateTime: 2020/7/16 2:15
 * @Description:
 */
@WebServlet("/updateUserServlet")
public class updateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        Map<String, String[]> parameterMap = request.getParameterMap();
        //3.封装User
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用service修改
        UserService service = new USerServiceImpl();
        service.UpadteUser(user);
        //5.跳转查询所有的Servlet
        response.sendRedirect(request.getContextPath()+"/FindUserBypageServlet?currentpage=1&rows=5");// /UserListServlet查询全部，过时
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
