package cn.xiaozheng.travel.service.impl;

import cn.xiaozheng.travel.dao.FavoriteDao;
import cn.xiaozheng.travel.dao.impl.FavoriteDaoImpl;
import cn.xiaozheng.travel.domain.Favorite;
import cn.xiaozheng.travel.service.FavoriteService;

/**
 * @Package: cn.xiaozheng.travel.service.impl
 * @ClassName: FavoriteServiceImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/12 21:06
 * @What_is_this_file_for: 实现收藏接口
 * @Description: 描述
 */
public class FavoriteServiceImpl  implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite != null;//如果对象有值，则为true,反之，则为false

    }

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    @Override
    public void addFavorite(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid), uid);
    }
}
