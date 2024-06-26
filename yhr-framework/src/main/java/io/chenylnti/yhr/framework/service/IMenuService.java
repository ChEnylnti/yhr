package io.chenylnti.yhr.framework.service;

import io.chenylnti.yhr.framework.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.vo.MenuWithRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-06
 */
public interface IMenuService extends IService<Menu> {

    RespBean getMenuByHrId();

    List<MenuWithRole> getAllMenusWithRole();
}
