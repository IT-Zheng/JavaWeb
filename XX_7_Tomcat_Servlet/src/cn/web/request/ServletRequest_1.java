package cn.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Package: cn.web.request
 * @ClassName: ServletRequest_1
 * @Author: Bad Body
 * @CreateTime: 2020/7/8 19:44
 * @Description: 获取请求头数据
 */
@WebServlet("/ServletRequest_1")
public class ServletRequest_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取所有请求头
        Enumeration<String> headerNames = req.getHeaderNames();//获取标题名称
        //2.遍历
        while (headerNames.hasMoreElements()){//判断否包含更多的元素
            String name = headerNames.nextElement();
            //根据名称获取请求头的值
            String value = req.getHeader(name);
            System.out.println("name = "+name+":"+"value = " + value);
        }
        //3.演示获取浏览器后的操作
        String header = req.getHeader("user-agent");
        if(header.contains("Firefox")){
            System.out.println("火狐来了！！！！！！");
        }else if ( header.contains("Chrome")) {
            System.out.println("谷歌来了！！！！！！");
        }else {
            System.out.println("未知浏览器！！！！！！！！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
