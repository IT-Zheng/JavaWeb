package cn.xiaozheng.travel.dao.impl;

import cn.xiaozheng.travel.dao.CategoryDao;
import cn.xiaozheng.travel.domain.Category;
import cn.xiaozheng.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.dao.impl
 * @ClassName: CategoryDaoImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/6 15:59
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 查询所有Category信息
     * @return
     */
    @Override
    public List<Category> findAll() {
        //定义sql
        String sql = "select * from tab_category";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
