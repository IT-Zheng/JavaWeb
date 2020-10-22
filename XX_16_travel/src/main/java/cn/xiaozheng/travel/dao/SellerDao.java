package cn.xiaozheng.travel.dao;

import cn.xiaozheng.travel.domain.Seller;

/**
 * @Package: cn.xiaozheng.travel.dao
 * @ClassName: SellerDao
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/12 14:32
 * @What_is_this_file_for: 所属商家
 * @Description: 描述
 */
public interface SellerDao {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Seller findById(int id);
}
