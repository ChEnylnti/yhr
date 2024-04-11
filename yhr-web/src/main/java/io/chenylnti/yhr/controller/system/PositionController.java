package io.chenylnti.yhr.controller.system;/**
 * @author chenylnti
 * @date 2024/4/10
 */

import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.RespPageBean;
import io.chenylnti.yhr.system.entity.Position;
import io.chenylnti.yhr.system.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public RespBean getPositionById(@PathVariable("id") Integer id) {
        return RespBean.ok(null,positionService.getById(id));
    }
    @GetMapping
    public RespPageBean getPositionsByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size) {
        return positionService.getPositionsByPage(page,size);
    }

    @PutMapping
    public RespBean updatePositionById(@RequestBody Position position) {
        return positionService.updateById(position)?RespBean.ok("更新成功"):RespBean.error("更新失败");
    }

    @PostMapping
    public RespBean addPosition(@RequestBody Position position) {
        return positionService.addPosition(position);
    }

    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id) {
        return positionService.deletePositionById(id);
    }
}
