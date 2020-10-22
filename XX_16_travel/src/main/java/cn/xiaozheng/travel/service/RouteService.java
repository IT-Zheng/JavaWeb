package cn.xiaozheng.travel.service;

import cn.xiaozheng.travel.domain.PageBean;
import cn.xiaozheng.travel.domain.Route;

/**
 * @Package: cn.xiaozheng.travel.service
 * @ClassName: RouteService
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/8 12:42
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public interface RouteService {
    /**
     * 查询PageBean分页对象
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return pageQuery
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(String rid);
}
