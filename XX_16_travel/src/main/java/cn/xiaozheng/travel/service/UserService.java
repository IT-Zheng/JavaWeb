package cn.xiaozheng.travel.service;

import cn.xiaozheng.travel.domain.User;

/**
 * @Package: cn.xiaozheng.travel.service
 * @ClassName: UserService
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/3 11:44
 * @What_is_this_file_for: UserService接口
 * @Description: 描述
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 激活码(激活的方法)
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
