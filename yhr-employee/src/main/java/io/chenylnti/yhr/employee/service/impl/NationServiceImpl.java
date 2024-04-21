package io.chenylnti.yhr.employee.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.chenylnti.yhr.employee.entity.Nation;
import io.chenylnti.yhr.employee.mapper.NationMapper;
import io.chenylnti.yhr.employee.service.INationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-17
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {


    @Override
    public List<Nation> getAllNations() {
        return baseMapper.selectList(Wrappers.emptyWrapper());
    }
}
