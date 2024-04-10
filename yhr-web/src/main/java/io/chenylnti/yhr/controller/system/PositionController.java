package io.chenylnti.yhr.controller.system;/**
 * @author chenylnti
 * @date 2024/4/10
 */

import io.chenylnti.yhr.framework.entity.RespPageBean;
import io.chenylnti.yhr.system.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ChEnylnti
 * @create: 2024-04-10 22:15
 * @Description:
 */

@RestController
@RequestMapping("/system/basic/position")
public class PositionController {
    @Autowired
    IPositionService positionService;
    @GetMapping
    public RespPageBean getPositionsByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size) {
        return positionService.getPositionsByPage(page,size);
    }
}
