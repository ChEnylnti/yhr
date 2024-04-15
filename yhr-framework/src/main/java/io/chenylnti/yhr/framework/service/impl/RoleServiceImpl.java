package io.chenylnti.yhr.framework.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.chenylnti.yhr.framework.entity.Role;
import io.chenylnti.yhr.framework.mapper.RoleMapper;
import io.chenylnti.yhr.framework.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chenylnti.yhr.framework.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getRolesExceptOwned(List<Role> roles) {
        List<Integer> roleIds = roles.stream().mapToInt(Role::getId).boxed().collect(Collectors.toList());
//        System.out.println(roleIds);
        if (roles.isEmpty()){
            return roleMapper.selectList(Wrappers.emptyWrapper());
        }else {
            return roleMapper.selectList(Wrappers.<Role>lambdaQuery().notIn(Role::getId, roleIds));
        }
    }

    @Override
    public boolean addRoleToCurrentHr(Integer rId,Integer hrId) {
        int i = roleMapper.addRoleToCurrentHr(hrId, rId);
        return i==1;
    }

    @Override
    public boolean deleteRoleByHrId(Integer hId, Integer rId) {
        int i = roleMapper.deleteRoleByHrId(hId,rId);
        return i==1;
    }


}
