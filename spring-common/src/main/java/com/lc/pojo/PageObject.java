package com.lc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageObject<T> implements Serializable {

    private static final long serialVersionUID = 6474832864884728893L;
        //总记录数
    private Integer rowCount;

        //当前页记录数
    private List<T> records;

        //总页数
    private Integer pageCount;

        //每页多少条
    private Integer pageSize;

        //当前页的页码值
    private Integer pageCurrent;

}
