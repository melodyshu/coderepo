package org.example.page;

import java.util.List;
//import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-06
 */
public class PageResult<T> {
    private Integer page = 1;// 要查找第几页
    private Integer pageSize = 20;// 每页显示多少条
    private Integer totalPage = 0;// 总页数
    private Long totalRows = 0L;// 总记录数
    private List<T> rows;// 结果集

    public PageResult() {
    }

    /*//IPage使用了mybatis-plus
    public PageResult(IPage<T> page) {
        this.setRows(page.getRecords());
        this.setTotalRows(page.getTotal());
        this.setPage((int) page.getCurrent());
        this.setPageSize((int) page.getSize());
    }*/

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
