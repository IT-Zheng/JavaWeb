package cn.Demo;

import cn.utility.JDBCutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCaffair {
    /**
     * 模拟事务的操作
     * @param args
     */
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ptemt = null;
        PreparedStatement ptemt1 = null;
        try {
            conn = JDBCutility.getConnection();
            conn.setAutoCommit(false);//在执行SQL之前开启事务

            String sql = "UPDATE account SET balance = balance - ? WHERE id = ?";
            String sql1 = "UPDATE account SET balance = balance + ? WHERE id = ?";

            ptemt = conn.prepareStatement(sql);
            ptemt1 = conn.prepareStatement(sql1);

            ptemt.setDouble(1,500);
            ptemt.setInt(2,1);

            ptemt1.setDouble(1,500);
            ptemt1.setInt(2,2);

            ptemt.executeUpdate();
            int i = 1/0;//人工制造异常
            ptemt1.executeUpdate();

            conn.commit();//没有异常 ，报错 ，事务提交
        } catch (Exception e) {//有事务时 抓取全部异常时用最大的类EXception
            try {
                if(conn != null){
                    conn.rollback();//出错，报异常，事务回滚
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCutility.close(ptemt,conn);
            JDBCutility.close(ptemt1,null);
        }

    }
}
