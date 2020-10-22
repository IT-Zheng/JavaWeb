package cn.xiaozheng.service.impl;

import cn.xiaozheng.dao.ProvinceDao;
import cn.xiaozheng.dao.impl.ProvinceDaoImpl;
import cn.xiaozheng.domain.Province;
import cn.xiaozheng.service.ProvinceService;
import cn.xiaozheng.util.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Package: cn.xiaozheng.service.impl
 * @ClassName: ProvinceServiceImpl
 * @Author: Bad Body
 * @CreateTime: 2020/7/28 0:39
 * @Description:
 */
public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao dao = new ProvinceDaoImpl();

    /**
     * 使用redis缓存
     * @return
     */
    @Override
    public String findAllJson() {
        //1.先从redis中查询数据
        //1.1获取redis客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        //2.判断 province_json 数据是否为null
        if (province_json == null || province_json.length() == 0) {
            //redis中没有数据
            System.out.println("redis中没有数据，查询数据库..");
            //2.1从数据中查询
            List<Province> ps = dao.findAll();
            //2.2将list序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(ps);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //2.3 将json存入redis
            jedis.set("province", province_json);
            jedis.close();
        }else{
            System.out.println("redis中有数据..");
        }
        return province_json;
    }

    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }
}
