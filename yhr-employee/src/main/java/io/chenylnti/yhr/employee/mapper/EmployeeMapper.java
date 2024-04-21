package io.chenylnti.yhr.employee.mapper;

import io.chenylnti.yhr.employee.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-17
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<Employee> getEmployeeByPage(@Param("page") Integer page,@Param("size") Integer size,@Param("emp") Employee employee,@Param("beginDateScope") Date[] beginDateScope);

    Long getTotal(@Param("emp") Employee employee,@Param("beginDateScope") Date[] beginDateScope);

    Integer maxWorkId();
}
