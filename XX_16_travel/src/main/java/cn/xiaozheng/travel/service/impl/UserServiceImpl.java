package cn.xiaozheng.travel.service.impl;

import cn.xiaozheng.travel.dao.UserDao;
import cn.xiaozheng.travel.dao.impl.UserDaoImpl;
import cn.xiaozheng.travel.domain.User;
import cn.xiaozheng.travel.service.UserService;
import cn.xiaozheng.travel.util.MailUtils;
import cn.xiaozheng.travel.util.UuidUtil;

/**
 * @Package: cn.xiaozheng.travel.service.impl
 * @ClassName: UserServiceImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/3 11:45
 * @What_is_this_file_for: UserService的实现类
 * @Description: 描述
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = dao.findByUserName(user.getUsername());
        //1.1判断u是否为null
        if (u != null) {
            //用户名存在，注册失败
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活的状态，唯一字符串
        user.setStatus("N");
        dao.vase(user);

        //3.激活邮件发送，

        String content = "<a href='http://localhost:81/travel/user/active?code=" + user.getCode() + "'>点击激活[旅游网]</a>";
        MailUtils.sendMail(user.getEmail(), content, "旅游网案例激活邮件");

        return true;
    }

    /**
     * 激活码(激活的方法)
     *
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
        User user = dao.findByCode(code);
        if (user != null) {
            //调用dao的修改激活状态的方法
            dao.updateStatus(user);
            return true;
        }
        return false;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return dao.findByUserNameAndPassword(user.getUsername(), user.getPassword());
    }
}
