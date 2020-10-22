package cn.xiaozheng.travel.dao.impl;

import cn.xiaozheng.travel.dao.RouteImgDao;
import cn.xiaozheng.travel.domain.RouteImg;
import cn.xiaozheng.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.dao.impl
 * @ClassName: RouteImgDaoImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/12 13:51
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public class RouteImgDaoImpl implements RouteImgDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据线路route的rid查询图片
     *
     * @param rid
     * @return
     */
    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ?";
        return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
    }
}
