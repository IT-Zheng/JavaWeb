package cn.xiaozheng.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Package: cn.xiaozheng.proxy
 * @ClassName: ProxyTest
 * @Author: Bad Body
 * @CreateTime: 2020/7/20 13:28
 * @Description:
 */
public class ProxyTest {
    public static void main(String[] args) {
        //1.创建真实方法
        ProxyObject proobject = new ProxyObject();
        /**
         * 类加载器：真实对象.getClass().getClassLoader()
         * 接口数组：真实对象.getClass().getInterfaces()
         * 业务逻辑处理：new InvocationHandler()
         */
        //动态代理增强ProxyObject对象
        SaleComputer proxy_object = (SaleComputer) Proxy.newProxyInstance(proobject.getClass().getClassLoader(), proobject.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 代理逻辑编写的方法：代理对象调用的所有方法都会触发该方法
             * @param proxy //代理对象，一般不用
             * @param method //代理对象调用的方法，被封装为的对象
             * @param args  //代理对象调用方法时，传递的实际参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*System.out.println("invoke被执行了！！！！！");
                System.out.println(method.getName());//方法名
                System.out.println(args[0]);//参数
                */

                    //判断是否是sale方法
                if(method.getName().equals("sale")){
                    //1.增强参数
                    double money = (double) args[0];
                    money = money * 0.85;
                    //使用真实对象调用该方法
                    Object object = method.invoke(proobject, money);
                    return object+"你被中间商赚了"+((double)args[0]-money)+"元";
                }else{
                    Object object = method.invoke(proobject, args);
                    return proxy;
                }
            }
        });


        //3.调用方法
        //String sale = object.sale(8000);
        String sale = proxy_object.sale(8000);
        System.out.println(sale);
    }
}
