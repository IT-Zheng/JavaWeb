package cn.xiaozheng.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/25 19:26
 * @Description:
 */
@WebServlet("/findUserServlet")
public class findUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //1.获取用户名
        String username = request.getParameter("username");
        //2.调用service层判断用户名是否存在
        Map<String, Object> map = new HashMap<String, Object>();

        if("tom".equals(username)){
            //存在
            map.put("userExist", "true");
            map.put("msg", "此用户名重复，请换一个");
        }else {
            //不存在
            map.put("userExist", "false");
            map.put("msg", "用户名可用");
        }
        //将MaP转为json，
        ObjectMapper mapper = new ObjectMapper();
        //并传递给客户端
        mapper.writeValue(response.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
