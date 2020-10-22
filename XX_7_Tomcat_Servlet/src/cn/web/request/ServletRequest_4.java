package cn.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: request
 * @ClassName: ServletRequest_4
 * @Author: Bad Body
 * @CreateTime: 2020/7/8 21:32
 * @Description: 其他的获取方法
 * 1.获取请求参数通用方式
 * 1. String getParameter(String name) :根据参数名称获取参数值   username=zs&password=123
 * 2. String[] getParametervalues (String name ) :根据参数名称获取参数值的数组 hobby=xx&hobby=game
 * 3. Enumeration<String> getParameterNames() :获取所有请求的参数名称
 * 4. Map<string, string[]> getParameterMap() :获取所有参数的map集合
 */
@WebServlet("/ServletRequest_4")
public class ServletRequest_4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post获取请求参数
        //根据参数名获取
//        String username = request.getParameter("username");
//        System.out.println("POSTusername = " + username);
//        //根据参数名获取参数的数组
//        String[] hobbies = request.getParameterValues("hobby");
//        for (String hobby : hobbies) {
//            System.out.println(hobby);
//        }
//        //获取所有请求的参数名称
//        Enumeration<String> parameterNames = request.getParameterNames();
//        while (parameterNames.hasMoreElements()){
//            String name = parameterNames.nextElement();
//            System.out.println(name);
//            String value = request.getParameter(name);
//            System.out.println(value);
//            System.out.println("-------------------------------");
//        }
//        //获取所有参数的map集合
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        //遍历
//        Set<String> strings = parameterMap.keySet();//把map的键放到Set集合中
//        for (String name : strings) {
//            //根据键获取值
//            String[] strings1 = parameterMap.get(name);
//            System.out.println(name);//键
//            for (String value : strings1) {//值
//                System.out.println(value);
//            }
//            System.out.println("-------------------------------");
//        }

        //获取request域中数据
        Object msg = request.getAttribute("msg");
        System.out.println(msg);
//        String header = request.getHeader("user-agent");
//        if(header.contains("Firefox")){
//            System.out.println("火狐来了！！！！！！");
//        }else if ( header.contains("Chrome")) {
//            System.out.println("谷歌来了！！！！！！");
//        }else {
//            System.out.println("未知浏览器！！！！！！！！");
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get获取请求参数
//        String username = request.getParameter("username");
//        System.out.println("GETusername = " + username);
        this.doPost(request,response);
    }
}
