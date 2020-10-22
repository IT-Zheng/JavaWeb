package cn.xiaozheng.travel.web.servlet;

import cn.xiaozheng.travel.domain.PageBean;
import cn.xiaozheng.travel.domain.Route;
import cn.xiaozheng.travel.domain.User;
import cn.xiaozheng.travel.service.FavoriteService;
import cn.xiaozheng.travel.service.RouteService;
import cn.xiaozheng.travel.service.impl.FavoriteServiceImpl;
import cn.xiaozheng.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/8 12:08
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{
    private final RouteService routeService = new RouteServiceImpl();
    private final FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        /**
         * 搜索框
         */
        //接收rname 线路名称
        String rname = request.getParameter("rname");
        //对get请求获取的数据重新编码
        if (rname != null && rname.length() > 0) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }else {
            rname = null;
        }

        //2.处理参数
        //类别id
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        //当前页码，如果不传递，则默认为第一页
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }
        //每页显示条数,如果不传递，默认每页显示5条记录
        int pageSize= 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }
        //3.调用service查询PageBean对象
            PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);
        //4.将pageBean对象序列化为json，返回
            writeValue(pb,response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收前端数据id
        String rid = request.getParameter("rid");
        //2.调用service查询route对象
        Route route = routeService.findOne(rid);
        //3.转换成json对象
        writeValue(route,response);

    }

    /**
     * 判断当前用户是否收藏过该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取线路id
        String rid = request.getParameter("rid");
        //2.获取当前登录的用户
        int uid;
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //用户未登录
            uid = 0;
        }else{
            //用户登录
            uid = user.getUid();
        }
        //3.调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        //4.写回客户端
        writeValue(flag,response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取线路rid
        String rid = request.getParameter("rid");
        //2.获取当前登录的用户
        int uid;
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //用户未登录
            return;
        }else{
            //用户登录
            uid = user.getUid();
        }
        //3.调用FavoriteService添加收藏
        favoriteService.addFavorite(rid, uid);
    }
}
