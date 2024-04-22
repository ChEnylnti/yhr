package io.chenylnti.yhr.controller.salary;/**
 * @author chenylnti
 * @date 2024/4/21
 */

import io.chenylnti.yhr.employee.entity.Employee;
import io.chenylnti.yhr.employee.entity.Salary;
import io.chenylnti.yhr.employee.entity.vo.EmployeeWithSalary;
import io.chenylnti.yhr.employee.service.impl.SalaryServiceImpl;
import io.chenylnti.yhr.framework.entity.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author:ChEnylnti
 * @create: 2024-04-21 13:53
 * @Description:
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    SalaryServiceImpl salaryService;

    @GetMapping
    public RespBean getAllSalaries() {
        List<Salary> salaries = salaryService.getBaseMapper().selectList(null);
        return salaries!=null&&!salaries.isEmpty()?RespBean.ok("查询成功",salaries):RespBean.error("查询失败");
    }
    @PostMapping
    public RespBean addSalary(@RequestBody Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
        int insert = salaryService.getBaseMapper().insert(salary);
        return insert>0?RespBean.ok("添加成功"):RespBean.error("添加失败");
    }
    @PutMapping
    public RespBean updateSalary(@RequestBody Salary salary) {
        int i = salaryService.getBaseMapper().updateById(salary);
        return i>0?RespBean.ok("更新成功"):RespBean.error("更新失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable Integer id) {
        List<EmployeeWithSalary> employeeList = salaryService.getSalaryOwns(id);
        if (employeeList!=null && !employeeList.isEmpty()) {
            return RespBean.error("还有员工在使用此帐套，删除失败");
        }
        int i = salaryService.getBaseMapper().deleteById(id);
        return i>0?RespBean.ok("删除成功"):RespBean.error("删除失败");
    }
}
