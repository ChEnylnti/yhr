package io.chenylnti.yhr.employee.service.impl;

import io.chenylnti.yhr.employee.entity.Employee;
import io.chenylnti.yhr.employee.entity.Salary;
import io.chenylnti.yhr.employee.entity.vo.EmployeeWithSalary;
import io.chenylnti.yhr.employee.mapper.EmployeeMapper;
import io.chenylnti.yhr.employee.mapper.SalaryMapper;
import io.chenylnti.yhr.employee.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {
    @Autowired
    SalaryMapper salaryMapper;

    @Override
    public void deleteSalaryEmp(Integer id) {
        salaryMapper.deleteSalaryEmp(id);
    }

    @Override
    public List<EmployeeWithSalary> getSalaryOwns(Integer id) {
        return salaryMapper.getSalaryOwns();
    }

    @Override
    public List<EmployeeWithSalary> getAllSalaryWithSalaryByPage(Integer page,Integer size,Employee employee) {
        return salaryMapper.getAllSalaryWithSalaryByPage(page,size,employee);
    }

    @Override
    public int updateEmpSalary(Integer eId, Integer sId) {
        return salaryMapper.updateEmpSalary(eId,sId);
    }

    @Override
    public int getEmpNum(Integer integer) {
        return salaryMapper.getEmpNum(integer);
    }

    @Override
    public int insertEmpSalary(Integer eId, Integer sId) {
        return salaryMapper.insertEmpSalary(eId,sId);
    }
}
