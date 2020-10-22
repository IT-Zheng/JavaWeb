package cn.LoginCase.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Package: cn.LoginCase.util
 * @ClassName: JDBCUtils
 * @Author: Bad Body
 * @CreateTime: 2020/7/9 2:14
 * @Description: JDBC工具类 使用Durid链接池
 */
public class JDBCUtils {
    private  static DataSource ds;

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
}
