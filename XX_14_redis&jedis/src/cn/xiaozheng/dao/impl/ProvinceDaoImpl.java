package cn.xiaozheng.dao.impl;

import cn.xiaozheng.dao.ProvinceDao;
import cn.xiaozheng.domain.Province;
import cn.xiaozheng.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Package: cn.xiaozheng.dao.impl
 * @ClassName: ProvinceDaoImpl
 * @Author: Bad Body
 * @CreateTime: 2020/7/28 0:36
 * @Description:
 */
public class ProvinceDaoImpl implements ProvinceDao {

    /**
     * 1.声明一个成员变量JdbcTemplate 获取连接池
     */
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        //定义sql
        String sql = "SELECT * FROM province";
        List<Province> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;
    }
}
