package cn.xiaozheng.travel.service.impl;

import cn.xiaozheng.travel.dao.FavoriteDao;
import cn.xiaozheng.travel.dao.RouteDao;
import cn.xiaozheng.travel.dao.RouteImgDao;
import cn.xiaozheng.travel.dao.SellerDao;
import cn.xiaozheng.travel.dao.impl.FavoriteDaoImpl;
import cn.xiaozheng.travel.dao.impl.RouteDaoImpl;
import cn.xiaozheng.travel.dao.impl.RouteImgDaoImpl;
import cn.xiaozheng.travel.dao.impl.SellerDaoImpl;
import cn.xiaozheng.travel.domain.PageBean;
import cn.xiaozheng.travel.domain.Route;
import cn.xiaozheng.travel.domain.RouteImg;
import cn.xiaozheng.travel.domain.Seller;
import cn.xiaozheng.travel.service.RouteService;

import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.service.impl
 * @ClassName: RouteServiceImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/8 12:42
 * @What_is_this_file_for: route_list页面展示的数据及分页栏
 * @Description: 描述
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao dao = new RouteDaoImpl();
    /**
     * 查询PageBean对象
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return pageQuery
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装PageBean
        PageBean<Route> pBean = new PageBean<Route> ();
        //设置当前页码
        pBean.setCurrentPage(currentPage);
        //设置每页显示条数
        pBean.setPageSize(pageSize);
        //通过dao.findTotalCount()返回总记录数
        int totalCount = dao.findTotalCount(cid,rname);
        //设置总记录数
        pBean.setTotalCount(totalCount);

        //计算开始的记录数
        int start = (currentPage - 1) * pageSize;
        //通过dao.findByPage()返回当前页码的数据集合
        List<Route> list = dao.findByPage(cid, start, pageSize,rname);
        //设置当前页显示的数据集合
        pBean.setList(list);
        
        //计算总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        //设置总页数
        pBean.setTotalPage(totalPage);
        System.out.println("totalCount = "+totalCount+",totalPage = "+totalPage+",totalPage="+totalPage+",cid = " + cid + ", currentPage = " + currentPage + ", pageSize = " + pageSize);
        System.out.println("list = "+list);
        return pBean;
    }

    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private final FavoriteDao favoriteDao = new FavoriteDaoImpl();
    /**
     * 根据id查询
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        //1.根据id去route表中查询route对象
        Route route = dao.findOne(Integer.parseInt(rid));
        //2.根据route的rid查询图片的集合
        List<RouteImg> listDaoByRid = routeImgDao.findByRid(route.getRid());
        //将集合设置到route对象中
        route.setRouteImgList(listDaoByRid);
        //根据route的sid查询买家的信息
        Seller sellerDaoById = sellerDao.findById(route.getSid());
        route.setSeller(sellerDaoById);

        /*
         * 查询收藏次数
         * 根据查询收藏表tab_favorite的rid字段的记录数获取次商品的收藏次数
         */
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
