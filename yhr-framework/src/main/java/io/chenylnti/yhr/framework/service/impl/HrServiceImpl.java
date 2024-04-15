package io.chenylnti.yhr.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.chenylnti.yhr.framework.entity.Hr;
import io.chenylnti.yhr.framework.mapper.HrMapper;
import io.chenylnti.yhr.framework.mapper.RoleMapper;
import io.chenylnti.yhr.framework.service.IHrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chenylnti.yhr.framework.utils.HrUtils;
import org.apache.catalina.mbeans.RoleMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements IHrService {

    @Autowired
    HrMapper hrMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //mybatis-plus
        QueryWrapper<Hr> qw = new QueryWrapper<>();
        qw.lambda().eq(Hr::getUsername,username);
        //查询某一个具体的用户
        Hr hr = getOne(qw);
        if (hr == null) {
            throw new UsernameNotFoundException("用户不存在，登录失败");
        }
        hr.setRoles(hrMapper.getHrRolesByHrId(hr.getId()));
        return hr;
    }

    @Override
    public List<Hr> getAllHrs(String keywords) {
        //查询出了当前用户的所以用户
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }

    @Override
    public int addHr(Hr hr) {
        return baseMapper.insert(hr);
    }

    @Override
    public int updateHr(Hr hr) {
        LambdaUpdateWrapper<Hr> eq = Wrappers.lambdaUpdate(Hr.class).eq(Hr::getId, hr.getId());
        return baseMapper.update(hr, eq);
    }

    @Override
    @Transactional
    public boolean deleteHrById(Integer id) {
        boolean result=true;
        hrMapper.deleteAllHrRoles(id);
        int i = hrMapper.deleteById(id);
        if (i!=1) {
            result = false;
        }
        return result;
    }
}
