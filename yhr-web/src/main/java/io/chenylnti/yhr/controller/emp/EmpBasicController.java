package io.chenylnti.yhr.controller.emp;/**
 * @author chenylnti
 * @date 2024/4/17
 */

import io.chenylnti.yhr.employee.entity.Employee;
import io.chenylnti.yhr.employee.entity.Joblevel;
import io.chenylnti.yhr.employee.entity.Nation;
import io.chenylnti.yhr.employee.entity.Politicsstatus;
import io.chenylnti.yhr.employee.service.impl.*;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.RespPageBean;
import io.chenylnti.yhr.system.entity.Department;
import io.chenylnti.yhr.system.entity.Position;
import io.chenylnti.yhr.system.service.impl.DepartmentServiceImpl;
import io.chenylnti.yhr.system.service.impl.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author:ChEnylnti
 * @create: 2024-04-17 09:55
 * @Description:
 */

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    NationServiceImpl nationService;

    @Autowired
    PoliticsstatusServiceImpl politicsstatusService;

    @Autowired
    JoblevelServiceImpl joblevelService;

    @Autowired
    PositionServiceImpl positionService;

    @Autowired
    DepartmentServiceImpl departmentService;

    @Autowired
    SalaryServiceImpl salaryService;

    @GetMapping
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
            , Employee employee, Date[] beginDateScope) {
        return employeeService.getEmployeeByPage(page,size,employee,beginDateScope);
    }

    @PostMapping
    public RespBean addEmployee(@RequestBody Employee employee) {
        if (employeeService.addEmp(employee)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }
    @PutMapping
    public RespBean updateEmployee(@RequestBody Employee employee) {
        boolean b = employeeService.updateById(employee);
        return b?RespBean.ok("更新成功"):RespBean.error("更新失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteEmployee(@PathVariable Integer id) {
        salaryService.deleteSalaryEmp(id);
        boolean b = employeeService.removeById(id);
        return b?RespBean.ok("删除成功"):RespBean.error("删除失败");
    }
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusService.getAllPoliticsstatus();
    }
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJobLevels() {
        return joblevelService.getAllJobLevels();
    }
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }
    @GetMapping("/deps")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
    @GetMapping("/depsWithOutChildren")
    public List<Department> getAllDepartmentsWithoutChildren() {
        return departmentService.getBaseMapper().selectList(null);
    }
    @GetMapping("/maxWorkId")
    public RespBean getMaxWorkId() {
        RespBean respBean = RespBean.build();
        respBean.setStatus(200);
        respBean.setData(String.format("%08d",employeeService.maxWorkId()+1));
        return respBean;
    }
}
