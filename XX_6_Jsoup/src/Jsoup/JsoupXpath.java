package Jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Package: Jsoup
 * @ClassName: JsoupXpath
 * @Author: Bad Body
 * @CreateTime: 2020/7/7 0:00
 * @Description: 有关Xpath的使用
 */
public class JsoupXpath {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        //获取Document对象，根据xml文档获取
        //获取Student.xml的path，类加载器
        String path = JsoupXpath.class.getClassLoader().getResource("Student.xml").getPath();
        //获取xml文档，加载文档进内存，获取Dom树-->Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
        //根据Document对象，创建JXDocument对象
        JXDocument jxDocument = new JXDocument(document);
        //JXDocument jxDocument = new JXDocument(document);
        //查询所有的student标签
        List<JXNode> st  = jxDocument.selN("//student");
        for (JXNode jxNode : st) {
            System.out.println(jxNode);
        }
        System.out.println("-----------------------------------------");
        //查询student标签下的name标签
        List<JXNode> stn  = jxDocument.selN("//student/name");
        for (JXNode jxNode : stn) {
            System.out.println(jxNode);
        }

        System.out.println("-----------------------------------------");
        //查询student标签下带id的name标签
        List<JXNode> stni  = jxDocument.selN("//student/name[@id]");
        for (JXNode jxNode : stni) {
            System.out.println(jxNode);
        }

        System.out.println("-----------------------------------------");
        //查询student标签下带有id属性的name标签 且id属性值为it
        List<JXNode> stnix  = jxDocument.selN("//student/name[@id='it']");
        for (JXNode jxNode : stnix) {
            System.out.println(jxNode);
        }
    }
}
