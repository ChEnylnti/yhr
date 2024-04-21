package io.chenylnti.yhr.employee.service;

import io.chenylnti.yhr.employee.entity.Salary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-17
 */
public interface ISalaryService extends IService<Salary> {

    void deleteSalaryEmp(Integer id);
}
