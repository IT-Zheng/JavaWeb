package reflex;

import domain.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {

    /**
     Class对象功能：
         * 获取功能：
         1. 获取成员变量们
             * Field[] getFields()
             * Field getField(String name)

             * Field[] getDeclaredFields()
             * Field getDeclaredField(String name)
         2. 获取构造方法们
             * Constructor<?>[] getConstructors()
             * Constructor<T> getConstructor(类<?>... parameterTypes)

             * Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
             * Constructor<?>[] getDeclaredConstructors()
         3. 获取成员方法们：
             * Method[] getMethods()
             * Method getMethod(String name, 类<?>... parameterTypes)

             * Method[] getDeclaredMethods()
             * Method getDeclaredMethod(String name, 类<?>... parameterTypes)

         4. 获取类名
             * String getName()



     */

    public static void main(String[] args) throws Exception {

        //0.获取Person的Class对象
        Class personClass = Person.class;

        /*
             1. 获取成员变量们
                 * Field[] getFields()
                 * Field getField(String name)

                 * Field[] getDeclaredFields()
                 * Field getDeclaredField(String name)
         */
        //1.Field[] getFields()获取所有public修饰的成员变量
        Field[] fields = personClass.getFields();//将变量封装成数组
        for (Field field:fields){
            System.out.println(field);
        }
        //2.Field getField(String name)获取指定名称的public修饰的成员变量
        System.out.println("-------------------------");
        Field a = personClass.getField("a");
        //获取成员变量a 的值
        Person p = new Person();
        Object value = a.get(p);
        System.out.println(value);
        //设置a的值
        a.set(p,"zhangsan");
        System.out.println(p);
        //Field[] getDeclaredFields()：获取所有的成员变量，不考虑修饰符
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declareField: declaredFields) {
            System.out.println("declareField = " + declareField);
        }
        //Field getDeclaredField(String name)
        Field d = personClass.getDeclaredField("d");
        //忽略访问权限修饰符的安全检查
        d.setAccessible(true);//强制反射
        Object Value2 = d.get(p);
        System.out.println("Value2 = " + Value2);
    /*-----------------------------------------------------------------*/
    /*获取构造方法*/
        Constructor constructor = personClass.getConstructor(String.class, int.class);
        System.out.println(constructor);
        //创造对象
        Object person = constructor.newInstance("张三",23);
        System.out.println("person = " + person);
    /*传递空参的值*/
        Object o = personClass.newInstance();
        System.out.println("o = " + o);
        //暴力反射
        //constructor.setAccessible(true);
    /*-----------------------------------------------------------------*/
    /*获取构造方法*/
        Method eat = personClass.getMethod("eat");
        Person p1 = new Person();
        //执行方法
        eat.invoke(p1);

        Method eat1 = personClass.getMethod("eat",String.class);
        //执行方法
        eat1.invoke(p1,"饭饭");

        System.out.println("------------------------------------");
        //获取所有public修饰的方法，会输出Object的方法
        Method[] methods = personClass.getMethods();
        for (Method method:methods) {
            System.out.println("method = " + method);
            String name = method.getName();
            System.out.println("name = " + name);
            //method.setAccessible(true);//暴力反射
        }

        //获取类名
        String className = personClass.getName();
        System.out.println(className);
    }


}
