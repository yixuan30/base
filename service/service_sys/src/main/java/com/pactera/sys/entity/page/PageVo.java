package com.pactera.sys.entity.page;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PageVo<T> {
    private long total;

    private List<T> rows=new ArrayList<>();

    public PageVo(long total, List<T> data) {
        this.total = total;
        this.rows = data;
    }
}
