package Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @author Bad Body
 * Document/Element对象功能
 */
public class JsoupDocuments {
    public static void main(String[] args) throws IOException {
        //获取Document对象，根据xml文档获取
        //获取Student.xml的path，类加载器
        String path = JsoupDocuments.class.getClassLoader().getResource("Student.xml").getPath();
        //获取xml文档，加载文档进内存，获取Dom树-->Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
        //获取元素对象，获取所有student元素 返回Elements对象,为数组类型
        Elements student = document.getElementsByTag("student");
        System.out.println(student);

        System.out.println("---------------------------------------");
        //获取id属性值为it的元素对象
        Elements idit = document.getElementsByAttributeValue("id","it");
        System.out.println(idit);

        System.out.println("---------------------------------------");

        //获取属性名为number的对象
        Elements number = document.getElementsByAttribute("number");
        System.out.println(number);

        System.out.println("---------------------------------------");

        //获取id属性值的元素对象
        Element it = document.getElementById("it");
        System.out.println(it);
    }
}
