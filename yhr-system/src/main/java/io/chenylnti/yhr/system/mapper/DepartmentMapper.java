package io.chenylnti.yhr.system.mapper;

import io.chenylnti.yhr.system.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chenylnti.yhr.system.entity.vo.DepartmentWithChildren;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-10
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartmentsByParentId(int id);


    void addDep(Department department);
}
