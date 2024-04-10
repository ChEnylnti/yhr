package io.chenylnti.yhr.framework.entity;/**
 * @author chenylnti
 * @date 2024/4/10
 */

import java.util.List;

/**
 * @author:ChEnylnti
 * @create: 2024-04-10 22:16
 * @Description:
 */

public class RespPageBean {
    private Long total;
    private List<?> data;

    public RespPageBean() {
    }

    public RespPageBean(Long total, List<?> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
