package cn.xiaozheng.travel.dao;

import cn.xiaozheng.travel.domain.Favorite;

/**
 * @Package: cn.xiaozheng.travel.dao
 * @ClassName: FavoriteDao
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/12 21:07
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public interface FavoriteDao {
    /**
     * 根据rid和uid查询收藏信息
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据查询收藏表tab_favorite的rid字段的记录数获取次商品的收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);
}
