package io.chenylnti.yhr.employee.service.impl;

import io.chenylnti.yhr.employee.entity.Employee;
import io.chenylnti.yhr.employee.mapper.EmployeeMapper;
import io.chenylnti.yhr.employee.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;



    @Override
    public RespPageBean getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page,size,employee,beginDateScope);
        Long total = employeeMapper.getTotal(employee,beginDateScope);
        return new RespPageBean(total, data);
    }

    @Override
    public int addEmp(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public Integer maxWorkId() {
        return employeeMapper.maxWorkId();
    }
}
