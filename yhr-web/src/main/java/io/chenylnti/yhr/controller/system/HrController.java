package io.chenylnti.yhr.controller.system;/**
 * @author chenylnti
 * @date 2024/4/14
 */

import io.chenylnti.yhr.framework.entity.Hr;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.Role;
import io.chenylnti.yhr.framework.entity.vo.HrWithRole;
import io.chenylnti.yhr.framework.service.impl.HrServiceImpl;
import io.chenylnti.yhr.framework.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:ChEnylnti
 * @create: 2024-04-14 13:15
 * @Description:
 */

@RestController()
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrServiceImpl hrService;
    @Autowired
    RoleServiceImpl roleService;

    @GetMapping()
    public RespBean getAllHrs( String keywords){
        List<Hr> hrs = hrService.getAllHrs(keywords);
//        System.out.println("36hrs"+hrs);
        return hrs!=null&&!hrs.isEmpty()?RespBean.ok("查找成功",hrs):RespBean.error("无Hr");
    }
    @PostMapping
    public RespBean addHr(@RequestBody Hr hr){
        return hrService.addHr(hr)>0?RespBean.ok("添加成功"):RespBean.error("添加失败");
    }
    @PutMapping
    public RespBean updateHr(@RequestBody Hr hr){
        if (hrService.updateHr(hr)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @PostMapping("/roles")
    public RespBean getRolesExceptOwned(@RequestBody List<Role> roles){
        List<Role> roleList = roleService.getRolesExceptOwned(roles);
        return roleList!=null&&!roleList.isEmpty()?RespBean.ok("加载roles成功",roleList):RespBean.error("加载roles失败");
    }
    @PostMapping("/addRole")
    public RespBean addRoleToCurrentHr(@RequestBody HrWithRole hrWithRole){
        boolean result=roleService.addRoleToCurrentHr(hrWithRole.getrId(),hrWithRole.gethId());
        return result?RespBean.ok("添加role成功"):RespBean.error("添加role失败");
    }
    @DeleteMapping("/deleteRole")
    public RespBean deleteRoleByHrId(@RequestBody HrWithRole hrWithRole){
        boolean result = roleService.deleteRoleByHrId(hrWithRole.gethId(),hrWithRole.getrId());
        return result?RespBean.ok("删除成功"):RespBean.error("删除失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id){
        boolean result = hrService.deleteHrById(id);
        return result?RespBean.ok("删除成功"):RespBean.error("删除失败");
    }
}
