package cn.xiaozheng.travel.dao.impl;

import cn.xiaozheng.travel.dao.RouteDao;
import cn.xiaozheng.travel.domain.Route;
import cn.xiaozheng.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.dao.impl
 * @ClassName: RouteDaoImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/8 12:43
 * @What_is_this_file_for: 内容部分的查询
 * @Description: 描述
 */
public class RouteDaoImpl implements RouteDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据cid查询总记录数
     *
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";
        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //存放SQL条件的值
        List params = new ArrayList();
        //2.判断参数是否有值
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
        System.out.println("cid = " + cid + ", rname = " + rname+
                ", sql（findTotalCount） = " + sql+", params = " + Arrays.toString(params.toArray()));
        return template.queryForObject(sql,Integer.class, params.toArray());
    }

    /**
     * 根据cid,start,pageSize查询当前页的数据集合
     *
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //String sql = "select * from tab_route where 1 = 1 and cid = ? and rname like ? limit ? , ?";
        //1.定义sql模板
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //存放SQL条件的值
        List params = new ArrayList();
        //2.判断参数是否有值
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like  ? ");
            params.add("%"+rname+"%");
        }
        //添加分页条件
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);
        sql = sb.toString();
        System.out.println("cid = " + cid + ", start = " + start +
                ", pageSize = " + pageSize + ", rname = " + rname+
                ", sql（findByPage） = " + sql+", params = " + Arrays.toString(params.toArray()));
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class), params.toArray());
    }

    /**
     * 根据id查询
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route> (Route.class),rid);
    }
}
