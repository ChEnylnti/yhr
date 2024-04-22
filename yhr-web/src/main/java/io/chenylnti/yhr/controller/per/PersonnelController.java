package io.chenylnti.yhr.controller.per;/**
 * @author chenylnti
 * @date 2024/4/22
 */

import io.chenylnti.yhr.employee.service.impl.SalaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
