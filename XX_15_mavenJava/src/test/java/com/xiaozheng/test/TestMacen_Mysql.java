package com.xiaozheng.test;

import com.xiaozheng.dao.Macen_MysqlDao;
import com.xiaozheng.dao.impl.Macen_MysqlDaoImpl;
import com.xiaozheng.domain.Macen_Mysql;
import org.junit.Test;

import java.util.List;

/**
 * @Package: com.xiaozheng.test
 * @ClassName: TestMacen_Mysql
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/2 17:42
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public class TestMacen_Mysql {
    @Test
    public void findAll(){
        Macen_MysqlDao macy = new Macen_MysqlDaoImpl();
        List<Macen_Mysql> list = macy.findall();
        for (Macen_Mysql macen_mysql : list) {
            System.out.println(macen_mysql);
        }
    }

}
