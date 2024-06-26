package io.chenylnti.yhr.framework.mapper;

import io.chenylnti.yhr.framework.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-06
 */
public interface RoleMapper extends BaseMapper<Role> {

    int addRoleToCurrentHr(Integer hId, Integer rId);

    int deleteRoleByHrId(Integer hId, Integer rId);
}
