package cn.xiaozheng.proxy;

/**
 * @Package: cn.xiaozheng.proxy
 * @ClassName: ProxyObject
 * @Author: Bad Body
 * @CreateTime: 2020/7/20 13:24
 * @Description:
 */

/**
 * 真实类
 */
public class ProxyObject implements SaleComputer{
    @Override
    public String sale(double money) {
        System.out.println("花了"+money+"买了个东西！！");
        return "电脑";
    }

    @Override
    public void show() {
        System.out.println("展示电脑");
    }
}
