package cn.web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Package: cn.web.servlet
 * @ClassName: ServletDome
 * @Author: Bad Body
 * @CreateTime: 2020/7/8 13:05
 * @Description:
 */
public class ServletDome implements Servlet {
    /**
     * 在Servlet被创建时，执行。只会执行一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("我被创造了！！！！！！！！！！！！");
    }

    /**
     * 获取ServletConfig对象
     * ServletConfig：Servlet的配置对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }



    /**
     * //提供服务的方法
     * 每一次Servlet被访问时，执行。执行多次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hell,word");
    }

    /**
     * 获取Servlet的一些信息，如版本，作者等等
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法
     * 在Servlet被杀死时，执行。只会执行一次
     * 在服务器正常关闭时执行，只会执行一次
     */
    @Override
    public void destroy() {
        System.out.println("我被销毁了！！！！！！！！！！！！");
    }
}
