package io.chenylnti.yhr.employee.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-17
 */
public class Employeetrain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工编号
     */
    private Integer eid;

    /**
     * 培训日期
     */
    private LocalDate trainDate;

    /**
     * 培训内容
     */
    private String trainContent;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public LocalDate getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(LocalDate trainDate) {
        this.trainDate = trainDate;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Employeetrain{" +
            "id = " + id +
            ", eid = " + eid +
            ", trainDate = " + trainDate +
            ", trainContent = " + trainContent +
            ", remark = " + remark +
        "}";
    }
}
