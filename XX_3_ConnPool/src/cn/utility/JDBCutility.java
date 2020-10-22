package cn.utility;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCutility {
    /**
     * 定义获取资源的成员名
     */
    private static String url;

    private static String user;

    private static String password;

    private static String driver;
    //连接池实现-生成一个Connection对象
    private static DataSource ds;

    /**
     * 文件读取，只需要读取一次即可拿到这些值。使用静态代码块
     */
    static {
        //读取资源文件，获取值

        try {
            //1.创建Properties集合类
            Properties pro = new Properties();
            Properties proDruid = new Properties();

            //获取src路径下的文件的方式：ClassLoader 类加载器，加载class字节码文件进内存
            ClassLoader classLoader = JDBCutility.class.getClassLoader();//随便一个字节码文件加载内存

            //URL res = classLoader.getResource("JDBCutility.properties");//传一个文件名 获取路径，返回对象为URL对象，统一资源定义符，定义一个文件的绝对路径
            URL res = classLoader.getResource("JDBCutility.properties");
            URL resDruid = classLoader.getResource("druid.properties");

            String path = res.getPath();//获取字符串路径
            String resDataSourcePath = resDruid.getPath();

            System.out.println("Path = " +path);
            System.out.println("resDataSourcePath = " + resDataSourcePath);

            //2.加载文件
            pro.load(new FileReader(path));
            proDruid.load(new FileReader(resDataSourcePath));

            //获取DataSource
            ds = DruidDataSourceFactory.createDataSource(proDruid);

            //3.获取数据
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //4.注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 获取连接
     * @return 连接池
     * @throws SQLException
     */
    public static Connection getDruidConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     * @param stm
     * @param conn
     */
    public static void close(Statement stm,Connection conn){
//        if(stm != null){
//            try {
//                stm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        //简化
        close(null,stm,conn);
    }

    /**
     * 释放资源
     * @param rs
     * @param stm
     * @param conn
     */
    public static void close(ResultSet rs,Statement stm,Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stm != null){
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池方法
     * @return ds
     */
    public static DataSource getDataSource(){
        return ds;
    }

}
