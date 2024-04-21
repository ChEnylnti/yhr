package io.chenylnti.yhr.framework.entity;/**
 * @author chenylnti
 * @date 2024/4/6
 */

/**
 * @author:ChEnylnti
 * @create: 2024-04-06 17:13
 * @Description: 返回类
 */

public class RespBean {
    private Integer status;
    private String message;
    private Object data;

    public static RespBean build(){
        return new RespBean();
    }


    public static RespBean ok(String message,Object data) {
       return new RespBean(200, message, data);
    }
    public static RespBean ok(String message) {
        return new RespBean(200, message, null);
    }
    public static RespBean error(String message,Object data) {
        return new RespBean(500, message, data);
    }
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }



    private RespBean() {
    }

    private RespBean(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
