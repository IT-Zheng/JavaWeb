package cn.xiaozheng.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/19 23:57
 * @Description:
 */
//@WebFilter("/*")
public class FilterDemo1 implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FiletDemo1被访问了！！！！！！！！！");
        chain.doFilter(req, resp);
        System.out.println("FiletDemo1回来了！！！！！！！！！！！");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
