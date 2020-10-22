package com.xiaozheng.dao.impl;

import com.xiaozheng.dao.Macen_MysqlDao;
import com.xiaozheng.domain.Macen_Mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.xiaozheng.dao.impl
 * @ClassName: Macen_MysqlDaoImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/2 17:08
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public class Macen_MysqlDaoImpl implements Macen_MysqlDao {

    @Override
    public List<Macen_Mysql> findall() {
        List<Macen_Mysql> list = new ArrayList<>();

        //获取Connection对象
        Connection connection = null;
        //获取操作数据库的对象
        PreparedStatement pot = null;
        //执行查询数据库
        ResultSet roSet = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //获取Connection对象
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java_web?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT","root","Zongroot");
            //获取操作数据库的对象
            pot = connection.prepareCall("select * from emp ");
            //执行查询数据库
            roSet = pot.executeQuery();

            while (roSet.next()){
                Macen_Mysql my = new Macen_Mysql();
                my.setId(roSet.getInt("id"));
                my.setEname(roSet.getString("ename"));
                my.setSalary(roSet.getDouble("salary"));

                list.add(my);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (roSet != null) {
                try {
                    roSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (pot != null) {
                try {
                    pot.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return list;
    }
}
