package cn.xiaozheng.service;

import cn.xiaozheng.domain.Login;
import cn.xiaozheng.domain.PageBean;
import cn.xiaozheng.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Package: cn.xiaozheng.servlet
 * @ClassName: UserService
 * @Author: Bad Body
 * @CreateTime: 2020/7/14 23:26
 * @Description: 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param li
     * @return
     */
    public Login login(Login li);

    /**
     * 保存用户数据
     * @param Us
     */
    void addUser(User Us);

    /**
     * 删除单个用户数据
     * @param id
     */
    void delUser(String id);

    /**
     * 根据id查找单一数据
     * @param id
     * @return User对象
     */
    User findUserById(String id);

    /**
     * 根据修改用户数据
     * @param user
     */
    void UpadteUser(User user);

    /**
     * 根据id批量删除数据
     * @param userId
     */
    void delsUser(String[] userId);

    /**
     * 分页查询
     * @param currentpage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentpage, String rows, Map<String, String[]> condition);
}
