package com.xiaozheng.domain;

/**
 * @Package: com.xiaozheng.domain
 * @ClassName: Macen_Mysql
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/2 16:41
 * @What_is_this_file_for: 这个文件是用来做什么的?
 * @Description: 描述
 */
public class Macen_Mysql {
    private int id;
    private String ename;
    private double salary;

    @Override
    public String toString() {
        return "Macen_Mysql{" +
                "id=" + id +
                ", ename='" + ename + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
