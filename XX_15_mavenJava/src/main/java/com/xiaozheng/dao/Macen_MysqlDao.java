package com.xiaozheng.dao;

import com.xiaozheng.domain.Macen_Mysql;

import java.util.List;

/**
 * @Package: com.xiaozheng.dao
 * @ClassName: Macen_MysqlDao
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/2 17:02
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public interface Macen_MysqlDao {
    public List<Macen_Mysql> findall();
}
