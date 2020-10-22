package cn.DataSource.Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCDruid {
    public static void main(String[] args){
        //1.导入jar包
        //2.配置文件
        //3.加载配置文件
        Properties pro = new Properties();
        InputStream is = JDBCDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4.获取连接池对象
        DataSource ds = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            ds = DruidDataSourceFactory.createDataSource(pro);
            //5.获取连接
            conn = ds.getConnection();
            String sql = "insert into account values(NULL,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"ma");
            pstmt.setDouble(2,1000);
            int count = pstmt.executeUpdate();
            System.out.println("count = " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
