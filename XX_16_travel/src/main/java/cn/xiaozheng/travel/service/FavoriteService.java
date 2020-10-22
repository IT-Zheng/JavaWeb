package cn.xiaozheng.travel.service;

/**
 * @Package: cn.xiaozheng.travel.service
 * @ClassName: FavoriteService
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/12 21:01
 * @What_is_this_file_for: 收藏类接口
 * @Description: 描述
 */
public interface FavoriteService {
    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(String rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void addFavorite(String rid, int uid);
}
