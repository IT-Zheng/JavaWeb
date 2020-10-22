package cn.xiaozheng.test;

import cn.xiaozheng.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Package: cn.xiaozheng.test
 * @ClassName: JedisTest
 * @Author: Bad Body
 * @CreateTime: 2020/7/27 0:59
 * @Description:
 */
public class JedisTest {

    private List<SitesInner> sites;

    /**
     * 快速入门
     */
    @Test
    public void test1(){
        //1.获取链接
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //2.操作
        jedis.set("username", "zhangsan");
        //3.关闭连接
        jedis.close();
    }

    /**
     * String 数据结构操作
     */
    @Test
    public void test2(){
        //1.获取链接
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //2.操作
        //存储
        jedis.set("username", "zhangsan");
        //获取
        String username = jedis.get("username");
        System.out.println(username);
        //可以使用setex方法指定过期时间的 k v
        jedis.setex("activecode", 20, "hehe");//20秒后删除K-V对
        //3.关闭连接
        jedis.close();
    }

    /**
     * hash 数据结构操作
     */
    @Test
    public void test3(){
        //1.获取链接
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //2.操作
        //存储
        jedis.hset("user", "name","李四");
        jedis.hset("user", "age","25");
        jedis.hset("user", "gender","男");
        //获取
        String name = jedis.hget("user", "name");
        System.out.println(name);
        Map<String, String> user = jedis.hgetAll("user");
        System.out.println(user);
        //keyset
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            //获取value值
            String value = user.get(key);
            System.out.println(key+":"+value);
        }
        //3.关闭连接
        jedis.close();
    }

    /**
     * list 数据结构操作
     */
    @Test
    public void test4(){
        //1.获取链接
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //2.操作
        //存储
        jedis.lpush("mylist", "a", "b", "c", "d");
        jedis.rpush("mylist", "a", "b", "c", "d");
        //获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);
        //list 弹出
        String mylist1 = jedis.lpop("mylist");
        System.out.println(mylist1);

        String mylist2 = jedis.rpop("mylist");
        System.out.println(mylist2);

        for (String s : mylist) {
            System.out.println(s);
        }
        jedis.close();
    }

    /**
     * set 数据结构操作
     */
    @Test
    public void test5(){
        //1.获取链接
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //2.操作
        //存储
        jedis.sadd("myset", "java", "c++", "php");
        //获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        for (String s : myset) {
            System.out.println(s);
        }
        jedis.close();
    }

    /**
     * sortedset 数据结构操作
     */
    @Test
    public void test6(){
        //1.获取链接
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //2.操作
        //存储
        jedis.zadd("mysort",3,"亚瑟");
        jedis.zadd("mysort",7,"后裔");
        jedis.zadd("mysort",4,"孙悟空");
        jedis.zadd("mysort",11,"妲己");
        jedis.zadd("mysort",1,"赵云");
        //获取
        Set<String> mysort = jedis.zrange("mysort", 0, -1);
        System.out.println(mysort);
        for (String s : mysort) {
            System.out.println(s);
        }
        jedis.close();
    }
    @Test
    public void test7(){
        //0.创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(50);
        //空闲时最大链接数
        config.setMaxIdle(10);
        //1.创建jdeis连接池
        JedisPool jedisPool = new JedisPool(config,"27.0.0.1",6379);
        //2.获取链接
        Jedis resource = jedisPool.getResource();
        //3.使用
        resource.set("zhangsan", "123");
        //4.关闭，归还链接
        resource.close();
    }

    @Test
    public void test8(){
        //通过连接池工具获取
        Jedis jedis = JedisPoolUtils.getJedis();
        //3.使用
        jedis.set("lisi", "123");
        //4.关闭，归还链接
        jedis.close();
    }

    public static class SitesInner {
        private String name;
        private String url;
    }
}
