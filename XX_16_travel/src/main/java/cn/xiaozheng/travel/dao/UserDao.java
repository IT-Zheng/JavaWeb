package cn.xiaozheng.travel.dao;

import cn.xiaozheng.travel.domain.User;

/**
 * @Package: cn.xiaozheng.travel.dao
 * @ClassName: UserDao
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/3 11:47
 * @What_is_this_file_for: UserDao的接口
 * @Description: 描述
 */
public interface UserDao {
    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUserName(String username);

    /**
     * 用户保存
     * @param user
     */
    public void vase(User user);

    /**
     * 按按激活码(Code)查询
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 修改指定用户激活状态
     */
    void updateStatus(User user);

    /**
     * 根据用户名，密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String username, String password);
}
