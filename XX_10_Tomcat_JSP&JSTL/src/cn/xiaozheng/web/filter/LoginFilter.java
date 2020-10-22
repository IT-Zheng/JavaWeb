package cn.xiaozheng.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/20 3:27
 * @Description:
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //1.强制转换→HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) req;
        //2.获取资源请求路径
        String uri = request.getRequestURI();
        //3.判断是否包含登录相关资源路径
        if (uri.contains("/login.jsp") || uri.contains("/LoginServlet") || uri.contains("/CheckCodeServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/")) {
            //包含，用户要登录，放行
            chain.doFilter(req,resp);
        }else {
            //不包含，验证用户是否登录
            Object user = request.getSession().getAttribute("username");
            if (user != null) {
                //登陆了,放行
                chain.doFilter(req, resp);
            }else{
                //没登陆，跳转登录页面
                request.getSession().setAttribute("login_msg","您尚未登陆,请登录！");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
