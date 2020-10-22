package cn.xiaozheng.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Package: cn.xiaozheng.Listener
 * @ClassName: ListenerContext
 * @Author: Bad Body
 * @CreateTime: 2020/7/21 13:25
 * @Description:
 */
public class ListenerContext implements ServletContextListener {
    /**
     * 监听ServletContext对象创建的
     * 在服务器启动后自动调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象被创建了！！！！！！！");
        //加载资源文件
        //1.获取ServletContext对象
        ServletContext context = sce.getServletContext();
        //2.加载资源文件
            //在xml文件中知道初始化的文件
        String initParameter = context.getInitParameter("namespace");//通过xml的键获取值的路径
        //3.获取真实路径
        String realPath = context.getRealPath(initParameter);
        //4.加载进内存
        try {
            FileInputStream fils = new FileInputStream(realPath);
            System.out.println(fils);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *在服务器关闭后，ServletContext对象被摧毁
     * 当服务器正常关闭后该方法被调用
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象被销毁了！！！！！！！");
    }
}
