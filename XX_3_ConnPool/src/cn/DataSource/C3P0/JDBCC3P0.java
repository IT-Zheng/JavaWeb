package cn.DataSource.C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCC3P0 {
    public static void main(String[] args) throws SQLException {
        //1.创建数据库连接池对象
        DataSource ds = new ComboPooledDataSource();//什么都不传使用默认的C3P0配置
        //DataSource ds = new ComboPooledDataSource("otherc3p0");//传参数则使用自定义的C3P0配置
        //2.获取连接对象
        Connection conn = ds.getConnection();

        //3.打印
        System.out.println("conn = " + conn);
        //testNamedConfig();//测试自定义配置
    }

    public static void testNamedConfig() throws SQLException {
        //1.创建数据库连接池对象
        //DataSource ds = new ComboPooledDataSource();//什么都不传使用默认的C3P0配置
        DataSource ds = new ComboPooledDataSource("otherc3p0");//传参数则使用自定义的C3P0配置
        //2.获取连接对象
        for (int i = 1; i <=10 ; i++) {
            Connection conn = ds.getConnection();
            //3.打印
            System.out.println(i+"---"+"conn = " + conn);
            if (i == 5) {
                conn.close();//资源归还给连接池
            }
        }
    }
}
