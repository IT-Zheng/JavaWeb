package Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @Package: Jsoup
 * @ClassName: JsoupElementsSelect
 * @Author: Bad Body
 * @CreateTime: 2020/7/7 0:00
 * @Description: 有关Element对象的select选择器查询操作
 */
public class JsoupElementsSelect {
    public static void main(String[] args) throws IOException {
        //获取Document对象，根据xml文档获取
        //获取Student.xml的path，类加载器
        String path = JsoupElementsSelect.class.getClassLoader().getResource("Student.xml").getPath();
        //获取xml文档，加载文档进内存，获取Dom树-->Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
        //查询name标签
        Elements name = document.select("name");
        System.out.println(name);

        System.out.println("--------------------------------");

        //查询id值为xiaozheng的元素
        Elements id = document.select("#xiaozheng");
        System.out.println(id);

        System.out.println("---------------------------------");

        //获取student标签并且number属性值为itcast_0001的age子标签
        Elements number = document.select("student[number=\"itcast_0001\"] > age");
        System.out.println(number);
    }
}
