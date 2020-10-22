package cn.xiaozheng.travel.dao.impl;

import cn.xiaozheng.travel.dao.SellerDao;
import cn.xiaozheng.travel.domain.Seller;
import cn.xiaozheng.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Package: cn.xiaozheng.travel.dao.impl
 * @ClassName: SellerDaoImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/12 14:34
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public class SellerDaoImpl implements SellerDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Seller findById(int id) {
        String sql = "select * from tab_seller where sid  = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
