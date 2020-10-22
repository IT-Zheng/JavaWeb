package cn.xiaozheng.service;

import cn.xiaozheng.domain.Province;

import java.util.List;

/**
 * @Package: cn.xiaozheng.service
 * @ClassName: ProvinceService
 * @Author: Bad Body
 * @CreateTime: 2020/7/28 0:37
 * @Description:
 */
public interface ProvinceService {
    public String findAllJson();
    public List<Province> findAll();
}
