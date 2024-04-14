package io.chenylnti.yhr.system.service;

import io.chenylnti.yhr.system.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import io.chenylnti.yhr.system.entity.vo.DepartmentWithChildren;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-10
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getAllDepartments();

    boolean addDepartment(Department department);

    boolean deleteDepartment(Integer id);
}
