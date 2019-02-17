package com.gdei.hsearch.service;

import java.util.List;

/**
 * 统一Service层返回结果
 * @param <T>
 */
public class ServiceMultiResult<T> {
    private long total; //方便做分页
    private List<T> result;

    public ServiceMultiResult(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }


    public int getResultSize() {
         if (this.result == null)
             return 0;
         return this.result.size();
    }

}
