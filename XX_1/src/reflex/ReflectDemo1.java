package reflex;

import domain.Person;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectDemo1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Person p = new Person();
//        p.eat();

        //1.加载配置文件
        //1.1创建Properties对象
        Properties pro = new Properties();
        //1.2加载配置文件，转换成一个集合
        //1.2.1获取class目录下的配置文件
        //getClassLoader类加载器
        //getResourceAsStream字节流
        ClassLoader classLoader = ReflectDemo1.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.Properties");
        pro.load(is);

        //2.获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //3.加载该类进内存  返回class对象
        Class cls = Class.forName(className);
        //4.创建对象
        Object obj = cls.newInstance();
        //5.获取方法
        Method method = cls.getMethod(methodName);
        //6.执行方法
        method.invoke(obj);
    }
}
