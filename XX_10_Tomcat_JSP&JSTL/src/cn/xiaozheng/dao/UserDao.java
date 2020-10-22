package cn.xiaozheng.dao;

import cn.xiaozheng.domain.Login;
import cn.xiaozheng.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Package: cn.xiaozheng.dao
 * @ClassName: UserDao
 * @Author: Bad Body
 * @CreateTime: 2020/7/14 23:30
 * @Description: 用户操作的Dao
 */
public interface UserDao {
    //查询全部信息
    public List<User> finAll();
    //用户登录查询
    public Login login(String username,String password);
    //用户添加数据
    void addUser(User us);
    //单个删除数据
    void delUser(String id);
    //通过id查找数据
    User findUserById(String id);
    //修改用户信息
    void UpdateUser(User user);
    //查询总记录数
    int dindUserByPage(Map<String, String[]> condition);
    //分页的每页数据
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
