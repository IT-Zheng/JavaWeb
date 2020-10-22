package cn.xiaozheng.dao.impl;
import cn.xiaozheng.dao.UserDao;
import cn.xiaozheng.domain.Login;
import cn.xiaozheng.domain.User;
import cn.xiaozheng.util.JDBCUtils;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Package: cn.xiaozheng.dao.impl
 * @ClassName: UserDaoImpl
 * @Author: Bad Body
 * @CreateTime: 2020/7/14 23:33
 * @Description:
 */
public class UserDaoImpl implements UserDao {

    //获取连接池
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    //实现接口的方法
    /**
     * 查询全部信息
     */
    public List<User> finAll() {
        //使用JDBC操作数据库
        //1.定义SQL
        String sql = "select * from usera";
        //执行查询语句，返回一个指定类型的数据（User）。
        List<User> query = template.query(sql, new BeanPropertyRowMapper<User>(User.class));//多结果集返回List集合
        return query;
    }

    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public Login login(String username,String password) {

        try {
            String sql = "select * from user where username = ? and password = ?";
            //执行查询语句，返回一个指定类型的数据（Login）。
            Login login = template.queryForObject(sql, new BeanPropertyRowMapper<Login>(Login.class), username, password);//单结果集返回对象
            return login;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加数据
     * @param us
     */
    @Override
    public void addUser(User us) {
        String sql = "insert into usera values(null,?,?,?,?,?,?)";
        template.update(sql, us.getName(), us.getGender(), us.getAge(), us.getAddress(), us.getQq(), us.getEmail());
    }

    /**
     * 删除数据
     * @param id
     */
    @Override
    public void delUser(String id) {
        String sql = "DELETE FROM usera WHERE id = ?";
        template.update(sql, Integer.parseInt(id));
    }

    /**
     * 查询单个数据用于修改页面
     * @param id
     * @return
     */
    @Override
    public User findUserById(String id) {
        try {
            String sql = "select * from usera where id = ?";
            //执行查询语句，返回一个指定类型的数据（User）。
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), Integer.parseInt(id));//单结果集
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修个数据
     * @param user
     */
    @Override
    public void UpdateUser(User user) {
        String sql = "update usera set name = ?,gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        template.update(sql,user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(),user.getId());
    }

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    @Override
    @Test
    public int dindUserByPage(Map<String, String[]> condition) {
        //1.定义模板初始化SQL
        //String sql = "select count(*)  from usera";
        String sql = "select count(*)  from usera where 1 = 1 ";
        StringBuilder StBu = new StringBuilder(sql);
        //2.遍历map,以区别是条件查询，还是正常的查询数据个数
        Set<String> keySet = condition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页的条件参数
            if ("currentpage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value) ) {
                //有值
                StBu.append(" and " + key + " like ?");
                params.add("%"+value+"%");//?号条件的值
            }
        }
        System.out.println(StBu.toString());
        System.out.println("params:"+ params);
        //执行查询语句，返回一个指定类型的数据（Integer）。
        return template.queryForObject(StBu.toString(),Integer.class,params.toArray());//单结果集，返回Integer类型自动拆箱为int类型
    }

    /**
     * 分页每页数据
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        try {
            String sql = "select * from usera where 1 = 1 ";
            StringBuilder builder = new StringBuilder(sql);
            //2.遍历map,以区别是条件查询，还是正常的查询数据个数
            Set<String> keySet = condition.keySet();
            //定义参数集合
            List<Object> params = new ArrayList<Object>();
            for (String key : keySet) {
                //排除分页的条件参数
                if ("currentpage".equals(key) || "rows".equals(key)){
                    continue;
                }
                //获取value
                String value = condition.get(key)[0];
                //判断value是否有值
                if (value != null && !"".equals(value) ) {
                    //有值
                    builder.append(" and " + key + " like ? ");
                    params.add("%"+value+"%");//?号条件的值
                }
            }
            //添加分页查询
            builder.append(" limit ? , ?");
            //添加分页查询参数值
            params.add(start);
            params.add(rows);
            //把字符容器转换成字符串
            sql = builder.toString();
            System.out.println(sql);
            System.out.println(params);
            //执行查询语句，返回一个指定类型的数据（User）。
            List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());//多结果集返回List集合
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
