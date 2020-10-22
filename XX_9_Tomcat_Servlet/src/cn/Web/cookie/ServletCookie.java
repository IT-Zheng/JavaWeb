package cn.Web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/13 11:51
 * @Description:
 */
@WebServlet("/ServletCookie")
public class ServletCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的消息体的数据及编码
        response.setContentType("text/html;charset=utf-8");
        // 设置是否找到对应Cookie的变量
        boolean fla = false;
        //1.获取所有Cookie
        Cookie[] cookies = request.getCookies();
        //2.遍历Cookie数组
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //3.获取cookie的名称
                String name = cookie.getName();
                //4.判断名称是否是：lastTime
                if ("lastTime".equals(name)){
                    //有该Cookie，表示不是第一次访问
                    fla = true;//有lastTimed Cookie 设置为true

                    //响应数据
                    //获取Cookie的value时间
                    String value = cookie.getValue();
                    System.out.println(value+"解码前");
                    //URL解码
                    value = URLDecoder.decode(value, "utf-8");
                    System.out.println(value+"解码后");
                    response.getWriter().write("欢迎回来，上次访问时间为："+value);

                    //设置Cookie的value
                    //获取当前时间的字符串，重新设置Cookie的值，重新发送
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//格式化时间格式
                    String str_date = simpleDateFormat.format(date);
                    //编码前的str_data数据
                    System.out.println(str_date+"编码前");
                    //Url编码
                    str_date = URLEncoder.encode(str_date, "utf-8");
                    System.out.println(str_date+"编码后");


                    cookie.setValue(str_date);//把值添加到Cookie中
                    //设置Cookie的存活时间
                    cookie.setMaxAge(60*60*24*60);//一个月
                    response.addCookie(cookie);//发送Cookie

                    break;
                }
            }
        }
        if (cookies == null || cookies.length == 0 || fla == false){
            //没有第一次访问
            //设置Cookie的value
            //获取当前时间的字符串，重新设置Cookie的值，重新发送
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//格式化时间格式
            String str_date = simpleDateFormat.format(date);
            //编码前的str_data数据
            System.out.println(str_date+"编码前");
            //Url编码
            str_date = URLEncoder.encode(str_date, "utf-8");
            System.out.println(str_date+"编码后");

            Cookie cookie = new Cookie("lastTime",str_date);
            cookie.setValue(str_date);//把值添加到Cookie中

            //设置Cookie的存活时间
            cookie.setMaxAge(60*60*24*60);//一个月
            response.addCookie(cookie);//发送Cookie
            System.out.println(str_date+"解码前");
            //URL解码
            str_date = URLDecoder.decode(str_date, "utf-8");
            System.out.println(str_date+"解码后");
            response.getWriter().write("欢迎首次访问，访问时间为："+str_date);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
