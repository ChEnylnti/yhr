package io.chenylnti.yhr.controller;/**
 * @author chenylnti
 * @date 2024/4/9
 */

import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ChEnylnti
 * @create: 2024-04-09 17:09
 * @Description: 用于返回前端的菜单栏
 */

@RestController
public class MenuController {

    @Autowired
    IMenuService menuService;

    @GetMapping("/menus")
    public RespBean getMenusByHrId(){
        return menuService.getMenuByHrId();
    }
}
