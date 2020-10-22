package cn.xiaozheng.travel.service.impl;

import cn.xiaozheng.travel.dao.CategoryDao;
import cn.xiaozheng.travel.dao.impl.CategoryDaoImpl;
import cn.xiaozheng.travel.domain.Category;
import cn.xiaozheng.travel.service.CategoryService;
import cn.xiaozheng.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Package: cn.xiaozheng.travel.service.impl
 * @ClassName: CategoryServiceImpl
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/6 16:29
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl();
    /**
     * 查询所有Category信息
     * @return
     */
    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2使用sortedset排序查询
        /*Set<String> categorys = jedis.zrange("category", 0, -1);*/
        //1.3查询sortedset中的分数（cid）和值（cname）
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        //2.判断查询结果是否为空
        if (categorys == null || categorys.size() == 0) {
            //空，从数据库查询获取数据，存入redis中
            System.out.println("在数据库中查询........");
            cs = dao.findAll();
            //将集合数据存储到redis中的 category的key
            for (Category c : cs) {
                //存储
                jedis.zadd("category", c.getCid(), c.getCname());
            }
        }else {
            System.out.println("在redis中查询..........");
            //不为空，将set的数据存入list
            cs = new ArrayList<Category>();
            for (Tuple tuple :categorys) {
                Category category = new Category();
                //将redis的数据转换成String类型存入Category中
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                //存储
                cs.add(category);
            }
        }
        return cs;
    }
}
