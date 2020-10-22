package cn.xiaozheng.travel.service;

import cn.xiaozheng.travel.domain.Category;

import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.service
 * @ClassName: CategoryService
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/6 16:27
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public interface CategoryService {
    /**
     * 查询所有Category信息
     * @return
     */
    public List<Category> findAll();
}
