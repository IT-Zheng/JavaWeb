package cn.Demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEmp {
    public static void main(String[] args) {
        List<Emp> list = new JDBCEmp().findAll();
        System.out.println(list);
    }

    /**
     * 查询所有Emp对象
     * @return
     */
    public List<Emp> findAll(){
        Connection conn = null;
        ResultSet resultSet = null;
        Statement stem = null;
        List<Emp> list = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取链接
            conn = DriverManager.getConnection("jdbc:mysql:///java_web?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT", "root", "Zongroot");
            String sql = "select * from emp";
            //4.
            stem = conn.createStatement();
            //5.
            resultSet = stem.executeQuery(sql);
            //6.
            Emp emp = null;
            //循环前创建集合对象，用于装在
            list = new ArrayList<Emp>();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String ename = resultSet.getString("ename");
                int job_id = resultSet.getInt("job_id");
                int mgr = resultSet.getInt("mgr");
                Date joindate = resultSet.getDate("joindate");
                double salary = resultSet.getDouble("salary");
                double bonus = resultSet.getDouble("bonus");
                int dept_id = resultSet.getInt("dept_id");
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                //装载集合
                list.add(emp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stem != null){
                try {
                    stem.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
