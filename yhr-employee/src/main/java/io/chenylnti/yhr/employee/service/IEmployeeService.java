package io.chenylnti.yhr.employee.service;

import io.chenylnti.yhr.employee.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.RespPageBean;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-17
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope);

    int addEmp(Employee employee);

    Integer maxWorkId();
}
