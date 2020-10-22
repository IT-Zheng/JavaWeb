package cn.xiaozheng.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/6 8:59
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法分发
        // 1.获取请求路径
        String uri = req.getRequestURI();
        System.out.println("请求URI："+uri);
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/')+1);
        System.out.println("方法名称："+methodName);
        //3.获取方法对象Method
        //(this)谁调用我，我代表谁
        System.out.println(this);
        try {
            /*
                //getDeclaredMethod忽略访问权限修饰符，获取方法
                Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                //4.执行方法
                //暴力反射
                method.setAccessible(true);
            */
            //修改被访问方法的修饰符
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private final ObjectMapper mapper = new ObjectMapper();
    /**
     * 直接将传入的对象序列化为json，并写回客户端
     * @param obj
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        //序列化到输出流，以json格式
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入的对象序列化为json，返回调用者
     * @param obj
     * @return
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        //序列化为字符串
        return mapper.writeValueAsString(obj);
    }
}
