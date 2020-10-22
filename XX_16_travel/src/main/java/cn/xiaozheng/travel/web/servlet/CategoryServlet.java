package cn.xiaozheng.travel.web.servlet;

import cn.xiaozheng.travel.domain.Category;
import cn.xiaozheng.travel.service.CategoryService;
import cn.xiaozheng.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/6 15:53
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private final CategoryService service = new CategoryServiceImpl();
    /**
     * 查询所有Category
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询所有
        List<Category> cs = service.findAll();
        //2.序列化json 并写回客户端
        writeValue(cs,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
