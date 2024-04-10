package io.chenylnti.yhr.controller;/**
 * @author chenylnti
 * @date 2024/4/6
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ChEnylnti
 * @create: 2024-04-06 09:47
 * @Description:
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello yhr";
    }
    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "/employee/basic/**";
    }
    @GetMapping("/employee/advanced/hello")
    public String hello3(){
        return "/employee/advanced/**";
    }
}
