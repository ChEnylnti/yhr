package io.chenylnti.yhr.employee.mapper;

import io.chenylnti.yhr.employee.entity.Employee;
import io.chenylnti.yhr.employee.entity.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chenylnti.yhr.employee.entity.vo.EmployeeWithSalary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<EmployeeWithSalary> getSalaryOwns();

    List<EmployeeWithSalary> getAllSalaryWithSalaryByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("emp") Employee employee);

    int updateEmpSalary(@Param("eId") Integer eId,@Param("sId") Integer sId);

    int getEmpNum(Integer id);

    int insertEmpSalary(Integer eId, Integer sId);
}
