package cn.DataSource.SpringJDBC;

import cn.utility.JDBCutility;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTempLate {
    /**
     * 用Spring JDBC
     * 1.资源用完自动释放资源
     * 2.连接自动归还连接池
     * 3.不需要申请连接
     * 4.内部作了自己的封装
     * 5.只需要考虑，如何定义SQL语句，如何执行，怎么处理
     * @param args
     */
    public static void main(String[] args) {
        //1.导入Jar包
        //2.创建JdbcTemplate
        JdbcTemplate template = new JdbcTemplate(JDBCutility.getDataSource());
        //3.调用方法
        String sql = "UPDATE account SET balance = 5000 WHERE id = ?";
        int conn = template.update(sql, 3);
        System.out.println("conn = " + conn);
    }
}
