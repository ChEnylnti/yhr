package io.chenylnti.yhr.framework.service.impl;

import io.chenylnti.yhr.framework.entity.Hr;
import io.chenylnti.yhr.framework.entity.Menu;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.vo.MenuVo;
import io.chenylnti.yhr.framework.entity.vo.MenuWithRole;
import io.chenylnti.yhr.framework.mapper.MenuMapper;
import io.chenylnti.yhr.framework.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public RespBean getMenuByHrId() {
        //获取当前登录成功的登录信息
        Hr principal = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MenuVo> menus = menuMapper.getMenusByHrId(principal.getId());
        return menus!=null&& !menus.isEmpty() ?RespBean.ok("menu加载成功",menus):RespBean.error("未加载到菜单数据");
    }

    @Override
    public List<MenuWithRole> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }
}
