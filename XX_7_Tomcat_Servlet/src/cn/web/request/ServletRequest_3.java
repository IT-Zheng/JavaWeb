package cn.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Package: request
 * @ClassName: ServletRequest_3
 * @Author: Bad Body
 * @CreateTime: 2020/7/8 21:07
 * @Description: 获取请求体数据，只有POST请求方式才有请求体
 * 1.BufferedReader getReader() :获取字符输入流，只能操作字符数据
 *      解决POST中文乱码方法:设置流的编码     request.setCharacterEncoding("utf-8");
 * 2.ServletInputstream getInputstream() :获取字节输入流，可以操作所有类型数据  常用于文件上传
 */
@WebServlet("/ServletRequest_3")
public class ServletRequest_3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置流的编码
        request.setCharacterEncoding("utf-8");
        //获取请求消息体
        //1.获取字符流
        BufferedReader Bureader = request.getReader();
        //2.读取数据
        String line = null;
        while ((line = Bureader.readLine()) != null){//按行读取数据
            System.out.println(line);
        }
        //结果：username=zhangsan&password=123&hobby=game&hobby=study&hobby=look
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //存储数据到request域中
        request.setAttribute("msg","hello");
        //请求转发
        request.getRequestDispatcher("/ServletRequest_4").forward(request,response);
    }
}
