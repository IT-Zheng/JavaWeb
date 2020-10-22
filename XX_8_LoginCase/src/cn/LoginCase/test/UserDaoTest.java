package cn.LoginCase.test;

import cn.LoginCase.dao.UserDao;
import cn.LoginCase.domain.User;
import org.junit.Test;

/**
 * @Package: cn.LoginCase.test
 * @ClassName: UserDaoTest
 * @Author: Bad Body
 * @CreateTime: 2020/7/9 15:18
 * @Description:
 */
public class UserDaoTest {
    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("zhangsan");
        loginuser.setPassword("123");

        UserDao userDao = new UserDao();
        User user = userDao.login( loginuser);

        System.out.println(user);

    }
}
