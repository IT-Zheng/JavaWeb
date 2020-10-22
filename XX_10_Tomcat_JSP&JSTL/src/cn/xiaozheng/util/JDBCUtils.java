package cn.xiaozheng.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Package: cn.LoginCase.util
 * @ClassName: cn.xiaozheng.util.JDBCUtils
 * @Author: Bad Body
 * @CreateTime: 2020/7/9 2:14
 * @Description: JDBC工具类 使用Durid链接池
 */
public class JDBCUtils {
    //定义成员变量
    // DataSource对象是得到一个连接的首选方式
    private static DataSource ds;

    static {
        try {
            //.加载配置文件到内存
            Properties pro = new Properties();
            //使用ClassLoader加载配置文件，进内存
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            //2.初始化连接池
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池
     * @return 连接池
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 获取连接Connection对象
     * @return 链接对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 归还连接池，释放资源
     * @param res
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet res, Statement stmt, Connection conn){
        if (res == null) {
            try {
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static void close(Statement stmt,Connection conn){
        close(null,stmt,conn);
    }
}
