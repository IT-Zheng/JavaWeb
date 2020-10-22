package cn.xiaozheng.travel.dao;

import cn.xiaozheng.travel.domain.Category;

import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.dao
 * @ClassName: CategoryDao
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/6 15:59
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public interface CategoryDao {
    /**
     * 查询所有Category信息
     * @return
     */
    public List<Category> findAll();
}
