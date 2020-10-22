package cn.xiaozheng.test;

import cn.xiaozheng.domian.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Package: cn.xiaozheng.test
 * @ClassName: jacksonTest
 * @Author: Bad Body
 * @CreateTime: 2020/7/25 13:39
 * @Description:
 */

public class jacksonTest {
    //java对象转为Json字符串
    @Test
    public void test1(){
        //创建Person对象
        Person p = new Person();
        p.setName("zhangsan");
        p.setAge(19);
        p.setGender("男");

        //2.创建Jackson的核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3.调用方法转换
        try {
            String s = mapper.writeValueAsString(p);
            System.out.println(s);
            mapper.writeValue(new File("a.txt"),p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        //创建Person对象
        Person p = new Person();
        p.setName("zhangsan");
        p.setAge(19);
        p.setGender("男");
        p.setBirthday(new Date());

        //2.转换
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = mapper.writeValueAsString(p);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test3(){
        //创建Person对象
        Person p = new Person();
        p.setName("zhangsan");
        p.setAge(19);
        p.setGender("男");
        p.setBirthday(new Date());

        Person p1 = new Person();
        p1.setName("zhangsan");
        p1.setAge(19);
        p1.setGender("男");
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setName("zhangsan");
        p2.setAge(19);
        p2.setGender("男");
        p2.setBirthday(new Date());

        //创建List集合
        List<Person> ps = new ArrayList<Person>();
        ps.add(p);
        ps.add(p1);
        ps.add(p2);

        //2.转换
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = mapper.writeValueAsString(ps);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test4(){
        //创建Person对象
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 20);
        map.put("gender", "男");
        map.put("Date", new Date());

        //2.转换
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = mapper.writeValueAsString(map);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    //演示json字符串转为java对象
    @Test
    public void test5(){
        //1.初始化json字符串
        String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":\"23\"}";
        //2.创建ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3.转换为java对象  person对象
        try {

            Person person = mapper.readValue(json, Person.class);
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
