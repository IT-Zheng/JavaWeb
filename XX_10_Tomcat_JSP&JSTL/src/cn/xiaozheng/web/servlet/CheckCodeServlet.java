package cn.xiaozheng.web.servlet;

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
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        int width = 80,height = 30;
        //1.创建一个对象，在内存中图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2.美化图片
        //2.1填充背景色
        Graphics graphics = image.getGraphics();//画笔对象
        graphics.setColor(Color.GRAY);//设置画笔颜色(灰色)
        graphics.fillRect(0,0,width,height);//设置填充的范围
        //2.2画边框
        graphics.setColor(Color.BLUE);//设置颜色
        graphics.drawRect(0,0,width-1,height-1);//设置画的矩形轮廓
        //2.3设置随机字母及数字
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        //2.3.1生成随机角标
        Random random = new Random();
            //构造一个字符容器
        StringBuilder stringBuilder = new StringBuilder();
        graphics.setColor(Color.YELLOW);//设置颜色(黄色)
        graphics.setFont(new Font("黑体",Font.BOLD,24));//设置字符大小
        for (int i = 1;i <=4 ;i++) {
            int index = random.nextInt(str.length());
            //2.3.2获取字符
            char c = str.charAt(index);
                // 向字符容器存入数据
            stringBuilder.append(c);
            System.out.print(c);
            //2.4写验证码
            graphics.drawString(c+"",width/6*i,25);
        }
        System.out.println();
            //将字符容器转换成字符串
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
