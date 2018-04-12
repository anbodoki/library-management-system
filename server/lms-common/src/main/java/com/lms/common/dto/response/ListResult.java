package com.lms.common.dto.response;

import java.io.Serializable;
import java.util.List;

public class ListResult<T> implements Serializable {

    private List<T> resultList;

    private int page;

    private int limit;

    private int offset;

    private long count;

    private long pageNum;

    public ListResult() {
    }

    public ListResult(int page, int limit, int offset, long count, long pageNum) {
        this.page = page;
        this.limit = limit;
        this.offset = offset;
        this.count = count;
        this.pageNum = pageNum;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public <E> ListResult<E> copy(Class<E> cls) {
        ListResult<E> result = new ListResult<>();
        result.setCount(getCount());
        result.setLimit(getLimit());
        result.setOffset(getOffset());
        result.setPage(getPage());
        result.setPageNum(getPageNum());
        return result;
    }
}
