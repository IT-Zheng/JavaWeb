package check;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

public class TesCheck {
    public static void main(String[] args) throws IOException {
        //1.创建计算器对象
        Calculator c = new Calculator();
        //2.获取字节码文件对象
        Class aClass = c.getClass();
        //3.获取所有方法
        Method[] methods = aClass.getMethods();

        int number = 0;//出现异常的次数
        //将文本写入到字符输出流中，缓冲字符，以便提供对单个字符、数组和字符串的有效写入
        BufferedWriter bw = new BufferedWriter(new /*构建了一个文件对象*/ FileWriter("bug.txt"));

        for (Method method : methods) {
            //4.判断方法上是否有Check注解
            if (method.isAnnotationPresent(Check.class)/*注解Check是否在此methods中的方法上。如果在则返回true；不在则返回false。*/) {
                //5.有，执行
                try {
                    method.invoke(c);//调用指定类中的指定方法
                } catch (Exception e) {
                    //6.捕获异常
                    number++;
                    bw.write(method.getName()/*获取异常方法的名称*/+"方法出现异常了");//写入字符串
                    bw.newLine();//写行分隔
                    bw.write("异常名称："+e.getCause()/*错误的原因*/.getClass()/*返回运行时类*/.getSimpleName()/*返回一个构造函数，方法的简单名称，或初始化*/);
                    bw.newLine();
                    bw.write("异常的原因："+e.getCause()/*错误的原因*/.getMessage()/*返回该错误的详细信息的字符串*/);
                    bw.newLine();
                    bw.write("----------------------------");
                    bw.newLine();
                }
            }
        }

        bw.write("本次测试共出现"+number+"次异常");

        bw.flush();//冲流
        bw.close();//关闭缓冲流
    }
}
