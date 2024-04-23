package io.chenylnti.yhr.controller.per;/**
 * @author chenylnti
 * @date 2024/4/22
 */

import io.chenylnti.yhr.employee.entity.Employee;
import io.chenylnti.yhr.employee.entity.Salary;
import io.chenylnti.yhr.employee.entity.vo.EmployeeWithSalary;
import io.chenylnti.yhr.employee.mapper.EmployeeMapper;
import io.chenylnti.yhr.employee.service.impl.SalaryServiceImpl;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.RespPageBean;
import io.chenylnti.yhr.framework.entity.dto.EmpWithSid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:ChEnylnti
 * @create: 2024-04-22 18:23
 * @Description:
 */

@RestController
@RequestMapping("/personnel/salary")
public class PersonnelController {
    @Autowired
    SalaryServiceImpl salaryService;
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping
    public RespPageBean getAllEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
            , Employee employee){
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        Long total = employeeMapper.getTotal(employee, null);
        List<EmployeeWithSalary> employeeWithSalaryList = salaryService.getAllSalaryWithSalaryByPage(page,size,employee);
        for (EmployeeWithSalary employeeWithSalary : employeeWithSalaryList) {
            if (employeeWithSalary.getSalary()==null){
                employeeWithSalary.setSalary(new Salary());
                employeeWithSalary.getSalary().setName("无");
            }
        }
        return new RespPageBean(total,employeeWithSalaryList);
    }
    @PutMapping
    public RespBean updateEmployeeSalary(@RequestBody EmpWithSid empWithSid){
        int empNum = salaryService.getEmpNum(empWithSid.geteId());
        int i;
        if (empNum != 0){
            i = salaryService.updateEmpSalary(empWithSid.geteId(), empWithSid.getsId());
        }else {
            i = salaryService.insertEmpSalary(empWithSid.geteId(),empWithSid.getsId());
        }
        return i>0?RespBean.ok("更新成功"):RespBean.error("更新失败");
    }
}
