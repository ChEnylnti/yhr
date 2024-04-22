package io.chenylnti.yhr.employee.entity.vo;/**
 * @author chenylnti
 * @date 2024/4/22
 */

import io.chenylnti.yhr.employee.entity.Employee;
import io.chenylnti.yhr.employee.entity.Salary;

/**
 * @author:ChEnylnti
 * @create: 2024-04-22 17:57
 * @Description:
 */

public class EmployeeWithSalary extends Employee {
    private Salary salary;

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}
