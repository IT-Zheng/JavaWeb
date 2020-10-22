package cn.xiaozheng.travel.dao;

import cn.xiaozheng.travel.domain.RouteImg;

import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.dao
 * @ClassName: RouteImgDao
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/12 13:49
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public interface RouteImgDao {

    /**
     * 根据线路route的rid查询图片
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);
}
