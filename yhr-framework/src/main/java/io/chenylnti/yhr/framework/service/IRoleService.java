package io.chenylnti.yhr.framework.service;

import io.chenylnti.yhr.framework.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-06
 */
public interface IRoleService extends IService<Role> {

    List<Role> getRolesExceptOwned(List<Role> roles);

    boolean addRoleToCurrentHr(Integer rId,Integer hrId);

    boolean deleteRoleByHrId(Integer hId, Integer rId);

}
