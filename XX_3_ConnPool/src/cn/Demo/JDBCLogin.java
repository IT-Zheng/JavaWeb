package cn.Demo;

import cn.utility.JDBCutility;

import java.sql.*;
import java.util.Scanner;

public class JDBCLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        boolean flag = new JDBCLogin().login2(username,password);
        if(flag){
            System.out.println("登录成功！");
        }else {
            System.out.println("用户名或密码错误！！");
        }
    }

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username,String password){
        if(username == null || password == null){
            return false;
        }
        Connection coon = null;//数据库连接对象
        Statement stm = null;//执行SQL的对象
        ResultSet rs = null;//结果集对象,封装查询结果
        String sql;//定义SQL语句
        try {
            coon = JDBCutility.getConnection();//获取连接 返回Connection对象
            sql = "SELECT * from user where username = '"+username+"' and password = '"+password+"'";//编写SQL语句
            stm = coon.createStatement();//获取执行SQL对象 返回Statement对象
            rs = stm.executeQuery(sql);//执行查询 返回ResultSet对象
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutility.close(rs,stm,coon);
        }
        return false;
    }

    /**
     * 登录方法 使用PreparedStatement对象
     * @param username
     * @param password
     * @return
     */
    public boolean login2(String username,String password){
        if(username == null || password == null){
            return false;
        }
        Connection coon = null;//数据库连接对象
        //Statement stm = null;//执行SQL的对象
        PreparedStatement pstm = null;
        ResultSet rs = null;//结果集对象,封装查询结果
        String sql;//定义SQL语句
        try {
            coon = JDBCutility.getConnection();//获取连接 返回Connection对象
            //sql = "SELECT * from user where username = '"+username+"' and password = '"+password+"'";//编写SQL语句
            sql = "SELECT * from user where username = ? and password = ?";//编写SQL语句
            //stm = coon.createStatement();//获取执行SQL对象 返回Statement对象
            pstm = coon.prepareStatement(sql);
            //rs = stm.executeQuery(sql);//执行查询 返回ResultSet对象
            pstm.setString(1,username);
            pstm.setString(2,password);
            rs = pstm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutility.close(rs,pstm,coon);
        }
        return false;
    }
}
