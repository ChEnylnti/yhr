package io.chenylnti.yhr.framework.mapper;

import io.chenylnti.yhr.framework.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.vo.MenuVo;
import io.chenylnti.yhr.framework.entity.vo.MenuWithRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-06
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuVo> getMenusByHrId(Integer hrId);

    List<MenuWithRole> getAllMenusWithRole();
}
