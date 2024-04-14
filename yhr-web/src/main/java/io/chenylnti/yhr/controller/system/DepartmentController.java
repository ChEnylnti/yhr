package io.chenylnti.yhr.controller.system;/**
 * @author chenylnti
 * @date 2024/4/10
 */

import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.system.entity.Department;
import io.chenylnti.yhr.system.entity.vo.DepartmentWithChildren;
import io.chenylnti.yhr.system.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:ChEnylnti
 * @create: 2024-04-10 22:15
 * @Description:
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentServiceImpl departmentService;

    @GetMapping
    public RespBean getAllDepartments() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        return allDepartments!=null&&!allDepartments.isEmpty()?RespBean.ok("查询成功",allDepartments):RespBean.error("未查到部门");
    }

    @PostMapping
    public RespBean addDepartment(@RequestBody Department department) {
        boolean b = departmentService.addDepartment(department);
        return b?RespBean.ok("添加成功"):RespBean.error("添加失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id) {
        boolean b = departmentService.deleteDepartment(id);
        return b?RespBean.ok("删除成功"):RespBean.error("删除失败，该部门还有员工或子部门");
    }
}
