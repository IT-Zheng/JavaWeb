package Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @Package: Jsoup
 * @ClassName: JsoupElements
 * @Author: Bad Body
 * @CreateTime: 2020/7/7 0:00
 * @Description: 有关Element对象的操作
 */
public class JsoupElements {
    public static void main(String[] args) throws IOException {
        //获取Document对象，根据xml文档获取
        //获取Student.xml的path，类加载器
        String path = JsoupElements.class.getClassLoader().getResource("Student.xml").getPath();
        //获取xml文档，加载文档进内存，获取Dom树-->Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
        //通过Document对象获取name标签，获取所有的name标签，可以获取2个
        Elements name = document.getElementsByTag("name");
        System.out.println(name.size());

        System.out.println("---------------------------------------");
        //通过Element对象获取子标签对象
        Element student = document.getElementsByTag("student").get(0);
        Elements st_name = student.getElementsByTag("name");
        System.out.println(st_name.size());

        System.out.println("---------------------------------------");
        //获取student对象的属性值
        String number = student.attr("number");
        System.out.println(number);
        //获取文本内容
        String text = st_name.text();
        String html = st_name.html();
        System.out.println(text);
        System.out.println(html);
    }
}
