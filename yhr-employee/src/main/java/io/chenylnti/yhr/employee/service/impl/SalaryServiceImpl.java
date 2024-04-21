package io.chenylnti.yhr.employee.service.impl;

import io.chenylnti.yhr.employee.entity.Salary;
import io.chenylnti.yhr.employee.mapper.SalaryMapper;
import io.chenylnti.yhr.employee.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
