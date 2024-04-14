package io.chenylnti.yhr.system.entity.vo;/**
 * @author chenylnti
 * @date 2024/4/13
 */

import io.chenylnti.yhr.system.entity.Department;

import java.util.List;
import java.util.Objects;

/**
 * @author:ChEnylnti
 * @create: 2024-04-13 11:01
 * @Description:
 */

public class DepartmentWithChildren extends Department {
    private List<Department> children;
    private Integer result;

    @Override
    public String toString() {
        return "DepartmentWithChildren{" +
                "name="+super.getName()+
                "id=" + super.getId()+
                "isParent="+super.getIsParent()+
                "parentId="+super.getParentId()+
                "children=" + children +
                ", result=" + result +
                '}';
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentWithChildren that = (DepartmentWithChildren) o;
        return Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(children);
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }
}
