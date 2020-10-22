package cn.xiaozheng.travel.domain;

import java.util.List;

/**
 * @Package: cn.xiaozheng.travel.domain
 * @ClassName: PageBean
 * @Author: 小政同学    QQ:xiaozheng666888@qq.com
 * @CreateTime: 2020/8/8 11:58
 * @What_is_this_file_for: 分页对象
 * @Description: 描述
 */
public class PageBean<T> {
    /**总记录数*/
    private int totalCount;
    /**总页数*/
    private int totalPage;
    /**当前页码*/
    private int currentPage;
    /**每页显示的条数*/
    private int pageSize;
    /**每页显示的数据集合*/
    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
