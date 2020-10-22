package Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Bad Body
 */
public class JsoupDemo {
    public static void main(String[] args) throws IOException {
        //获取Document对象，根据xml文档获取
            //获取Student.xml的path，类加载器
        String path = JsoupDemo.class.getClassLoader().getResource("Student.xml").getPath();
            //获取xml文档，加载文档进内存，获取Dom树-->Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
            //获取元素name，返回Elements对象,为数组类型
        Elements htName = document.getElementsByTag("name");
            //打印htName数组的长度
        System.out.println(htName.size());
            //获取第一个name的Element对象
        Element element = htName.get(0);
            //获取数据
        String text = element.text();
            //输出内容
        System.out.println("text = " + text);
    }
    //获取HTML文档的方法
    @Test
    public void tesurl() throws IOException {
        URL url = new URL("https://www.bixia.org/0_353/2966654.html");
        Document doUrl = Jsoup.parse(url, 10000);
        Element content = doUrl.getElementById("content");
        String text = content.text();
        System.out.println(text);
    }
}
