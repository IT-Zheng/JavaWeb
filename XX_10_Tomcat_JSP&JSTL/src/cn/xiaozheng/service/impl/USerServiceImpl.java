package cn.xiaozheng.service.impl;

import cn.xiaozheng.dao.UserDao;
import cn.xiaozheng.dao.impl.UserDaoImpl;
import cn.xiaozheng.domain.Login;
import cn.xiaozheng.domain.PageBean;
import cn.xiaozheng.domain.User;
import cn.xiaozheng.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @Package: cn.xiaozheng.servlet.impl
 * @ClassName: USerServiceImpl
 * @Author: Bad Body
 * @CreateTime: 2020/7/14 23:29
 * @Description:
 */
public class USerServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.finAll();
    }

    @Override
    public Login login(Login li) {
        //调用Dao完成登录
        return dao.login(li.getUsername(), li.getPassword());
    }

    @Override
    public void addUser(User Us) {
        //调用Dao完成添加数据
        dao.addUser(Us);
    }

    @Override
    public void delUser(String id) {
        //调用Dao完成单个删除数据
        dao.delUser(id);
    }

    @Override
    public User findUserById(String id) {
        //调用Dao完成通过id查找数据

        return dao.findUserById(id);
    }

    @Override
    public void UpadteUser(User user) {
        dao.UpdateUser(user);
    }

    /**
     * 根据id批量删除数据
     *
     * @param userId
     */
    @Override
    public void delsUser(String[] userId) {
        for (String s : userId) {
            //调用原有的单一删除数据
            dao.delUser(s);
        }
    }

    /**
     * 分页查询
     *条件查询
     * @param CurrentPage
     * @param Rows
     * @param condition
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String CurrentPage, String Rows, Map<String, String[]> condition) {
        //1.创建空的PageBean对象
        PageBean<User> PB = new PageBean<User>();
        //2.字符串转数字
        int currentpage = Integer.parseInt(CurrentPage);
        int rows = Integer.parseInt(Rows);

        //3.调用Dao查询总记录数
        int totalCount = dao.dindUserByPage(condition);//将分页总数据存入PageBen对象中
        PB.setTotalCount(totalCount);
        //4.调用Dao查询List集合
            //计算开始记录的索引
        int start = (currentpage - 1) * rows;
        List<User> list = dao.findByPage(start, rows,condition);
        PB.setList(list);//将分页的每页数据存入PageBen对象中
        //5.计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows)+1;
        PB.setTotaPage(totalPage);
        //判断参数，并设置值
        currentpage = currentpage < 1 ? 1 : currentpage;
        currentpage = currentpage > totalPage ? totalPage : currentpage;
        PB.setCurrentPage(currentpage);
        PB.setRows(rows);
        return PB;
    }

}
