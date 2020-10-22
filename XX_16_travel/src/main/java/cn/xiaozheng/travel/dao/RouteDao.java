package cn.xiaozheng.travel.dao;

import cn.xiaozheng.travel.domain.Route;

import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.dao
 * @ClassName: RouteDao
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/8 12:43
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public interface RouteDao {
    /**
     * 根据cid查询总记录数
     * @param cid
     * @param rname
     * @return
     */
    public int findTotalCount(int cid,String rname);

    /**
     * 根据cid,start,pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
