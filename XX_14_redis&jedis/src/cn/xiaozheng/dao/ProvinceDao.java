package cn.xiaozheng.dao;

import cn.xiaozheng.domain.Province;

import java.util.List;

/**
 * @Package: cn.xiaozheng.dao
 * @ClassName: ProvinceDao
 * @Author: Bad Body
 * @CreateTime: 2020/7/28 0:35
 * @Description:
 */
public interface ProvinceDao {

    public List<Province> findAll();
}
