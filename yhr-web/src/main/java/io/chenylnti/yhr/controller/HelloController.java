package io.chenylnti.yhr.controller;/**
 * @author chenylnti
 * @date 2024/4/6
 */

import io.chenylnti.yhr.system.HelloService;
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
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello(){
        return helloService.hello();
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
