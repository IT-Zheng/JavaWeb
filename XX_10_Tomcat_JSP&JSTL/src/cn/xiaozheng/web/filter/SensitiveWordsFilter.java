package cn.xiaozheng.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.xiaozheng.web.filter
 * @ClassName: SensitiveWordsFilter
 * @Author: Bad Body
 * @CreateTime: 2020/7/20 23:14
 * @Description: 敏感词汇过滤器
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    private List<String> list = new ArrayList<String>();//敏感词汇集合
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        try {
            //1.获取文件真实路径
            ServletContext context = filterConfig.getServletContext();
            String realPath = context.getRealPath("/WEB-INF/classes/mgch.txt");
            //2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3.将文件的每一行数据添加到list里面
            String line = null;
            while ((line = br.readLine()) != null){
                list.add(line);
            }
            br.close();
            System.out.println("SensitiveWordsFilter:"+list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.创建代理对象，增强getParameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(), servletRequest.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断是否是getParameter
                if (method.getName().equals("getParameter")) {
                    //增强返回
                    //获取返回值
                    String value = (String) method.invoke(servletRequest,args);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)){
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                }

                return method.invoke(servletRequest, args);
            }
        });
        //2.放行
        filterChain.doFilter(proxy_req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
