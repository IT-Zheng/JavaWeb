package cn.DataSource.SpringJDBC;

import cn.utility.JDBCutility;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCTempLate1 {
    //获取JdbcTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JDBCutility.getDataSource());//其他类不能获取

    String sql = null;
    int count;
    /**
     * 修改Emp表1001号员工的工资
     */
    @Test
    public void testUpdate(){
        //定义SQL
        sql = "update emp set salary = 10000 where id = 1001";
        //执行SQL
        count = template.update(sql);
        System.out.println("count = " + count);

    }

    /**
     * 添加一条记录
     */
    @Test
    public void testInsert(){
        sql = "insert into emp(id,ename,dept_id) values(?,?,?)";
        count = template.update(sql, 1016, "郭靖", 10);
        System.out.println("count = " + count);

    }

    /**
     * 删除一条记录
     */
    @Test
    public void testDelete(){
        sql = "DELETE FROM emp WHERE id = ?";
        count = template.update(sql, 1016);
        System.out.println("count = " + count);

    }

    /**
     * 查询记录，将其封装成Map对象
     * 这个方法结果集长度为1
     */
    @Test
    public void testSelectMap(){
        sql = "SELECT * FROM emp WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, 1001);
        System.out.println("map = " + map);
        //map = {id=1001, ename=孙悟空, job_id=4, mgr=1004, joindate=2000-12-17, salary=10000.00, bonus=null, dept_id=20}
    }

    /**
     * 查询所有记录封装成List
     *
     */
    @Test
    public void testSelectList(){
        sql = "SELECT * FROM emp";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println("stringObjectMap = " + stringObjectMap);
        }
    }

    /**
     *查询所有记录，再将其封装为DBCTempLateEmp对象的List集合
     *
     */
    @Test
    public void testSelectEmpList(){
        sql = "SELECT * FROM emp";
        List<JDBCTempLateEmp> list = template.query(sql, new RowMapper<JDBCTempLateEmp>() {
            @Override
            public JDBCTempLateEmp mapRow(ResultSet resultSet, int i) throws SQLException {
                JDBCTempLateEmp emp = new JDBCTempLateEmp();
                int id = resultSet.getInt("id");
                String ename = resultSet.getString("ename");
                int job_id = resultSet.getInt("job_id");
                int mgr = resultSet.getInt("mgr");
                Date joindate = resultSet.getDate("joindate");
                double salary = resultSet.getDouble("salary");
                double bonus = resultSet.getDouble("bonus");
                int dept_id = resultSet.getInt("dept_id");

                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);
                return emp;
            }
        });
        for (JDBCTempLateEmp emp : list) {
            System.out.println("emp = " + emp);
        }
    }

    /**
     *查询所有记录，再将其封装为DBCTempLateEmp对象的List集合
     * Spring JDBC自动封装
     *query的参数：RowMapper
     * 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
     * new BeanPropertyRowMapper<类型>(类型.class)
     */
    @Test
    public void testSelectEmpList1(){
        sql = "SELECT * FROM emp";
        List<JDBCTempLateEmp> list = template.query(sql, new BeanPropertyRowMapper<JDBCTempLateEmp>(JDBCTempLateEmp.class));
        for (JDBCTempLateEmp emp : list) {
            System.out.println("emp = " + emp);
        }

    }
    /**
     *queryForObject一般用于执行聚合函数
     */
   @Test
    public void testSelectEmpListCount(){
        sql = "SELECT COUNT(id) FROM emp";
        Long aLong = template.queryForObject(sql, Long.class);
        System.out.println("aLong = " + aLong);

    }
}
