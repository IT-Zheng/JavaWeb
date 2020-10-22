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
     * ����ServletContext���󴴽���
     * �ڷ������������Զ�����
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext���󱻴����ˣ�������������");
        //������Դ�ļ�
        //1.��ȡServletContext����
        ServletContext context = sce.getServletContext();
        //2.������Դ�ļ�
            //��xml�ļ���֪����ʼ�����ļ�
        String initParameter = context.getInitParameter("namespace");//ͨ��xml�ļ���ȡֵ��·��
        //3.��ȡ��ʵ·��
        String realPath = context.getRealPath(initParameter);
        //4.���ؽ��ڴ�
        try {
            FileInputStream fils = new FileInputStream(realPath);
            System.out.println(fils);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *�ڷ������رպ�ServletContext���󱻴ݻ�
     * �������������رպ�÷���������
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext���������ˣ�������������");
    }
}
