package cn.xiaozheng.domain;

import java.util.List;

/**
 * @Package: cn.xiaozheng.domain
 * @ClassName: PageBean
 * @Author: Bad Body
 * @CreateTime: 2020/7/16 15:40
 * @Description: 分页对象
 */
public class PageBean<T> {
    int totalCount;//总记录数
    int totaPage;//总页码
    List<T> list;//每页的记录
    int currentPage;//当前页码

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totaPage=" + totaPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    int rows;//每页显示的记录数



    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotaPage() {
        return totaPage;
    }

    public void setTotaPage(int totaPage) {
        this.totaPage = totaPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
