package io.chenylnti.yhr.employee.mapper;

import io.chenylnti.yhr.employee.entity.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-17
 */
public interface SalaryMapper extends BaseMapper<Salary> {

    void deleteSalaryEmp(Integer id);
}
