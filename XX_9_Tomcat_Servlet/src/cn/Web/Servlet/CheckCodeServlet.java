package cn.Web.Servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: Bad Body
 * @CreateTime: 2020/7/12 0:26
 * @Description:
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100,height = 50;
        //1.创建一个对象，在内存中图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2.美化图片
        //2.1填充背景色
        Graphics graphics = image.getGraphics();//画笔对象
        graphics.setColor(Color.pink);//设置画笔颜色
        graphics.fillRect(0,0,width,height);//设置填充的范围
        //2.2画边框
        graphics.setColor(Color.BLUE);//设置颜色
        graphics.drawRect(0,0,width-1,height-1);//设置画的矩形轮廓
        //2.3设置随机字母及数字
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        //2.3.1生成随机角标
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        graphics.setColor(Color.BLACK);//设置颜色
        for (int i = 1;i <=4 ;i++) {
            int index = random.nextInt(str.length());
            //2.3.2获取字符
            char c = str.charAt(index);
            stringBuilder.append(c);
            System.out.print(c);
            //2.4写验证码
            graphics.drawString(c+"",width/5*i,height/2);
        }
        System.out.println();
            //将验证码存入Session
        String checkCode_session = stringBuilder.toString();
        //将验证码存入session
        request.getSession().setAttribute("checkCode_session",checkCode_session);

        //2.5画线干扰
        graphics.setColor(Color.GREEN);//设置颜色

        for (int i = 0; i<10 ;i++) {
            //2.5.1随机生成坐标
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            //2.5.2写线
            graphics.drawLine(x1,y1,x2,y2);
        }


        //3.将内存的图片输出到页面展示或者任意流中
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
