package cn.LoginCase.dao;

import cn.LoginCase.domain.User;
import cn.LoginCase.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Package: cn.LoginCase.dao
 * @ClassName: UserDao
 * @Author: Bad Body
 * @CreateTime: 2020/7/9 2:11
 * @Description: 操作数据库中User表的类
 */
public class UserDao {

    //声明JDBCTemplate对象公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 登录方法
     * @param loginUser
     * @return User包含用户的所有数据
     */
    public User login(User loginUser){
        System.out.println(loginUser+"Dao");
        try {
            //1.编写SQL
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword());//返回结果集对象，类型为User的映射
            System.out.println(user+"User user");
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//一般打印日志，不答应次异常
            return null;
        }
    }
}
