package cn.xiaozheng.travel.dao.impl;

import cn.xiaozheng.travel.dao.UserDao;
import cn.xiaozheng.travel.domain.User;
import cn.xiaozheng.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Package: cn.xiaozheng.travel.dao.impl
 * @ClassName: UserDaoImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/3 11:47
 * @What_is_this_file_for: UserDao的实现类
 * @Description: 描述
 */
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 用户保存
     * @param user
     */
    @Override
    public void vase(User user) {
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //执行sql
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /**
     * 按按激活码(Code)查询
     *
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            //定义SQL
            String sql = "select * from tab_user where code = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 修改指定用户激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        //定义sql
        String sql = "update tab_user set status = 'Y' where uid = ?";
        //执行sql
        template.update(sql, user.getUid());
    }

    /**
     * 根据用户名，密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUserNameAndPassword(String username, String password) {
        User user = null;
        try {
            //1.编写sql
            String sql = "select * from tab_user where username = ? and password = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }
}
