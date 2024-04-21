package io.chenylnti.yhr.employee.service.impl;

import io.chenylnti.yhr.employee.entity.Politicsstatus;
import io.chenylnti.yhr.employee.mapper.PoliticsstatusMapper;
import io.chenylnti.yhr.employee.service.IPoliticsstatusService;
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
public class PoliticsstatusServiceImpl extends ServiceImpl<PoliticsstatusMapper, Politicsstatus> implements IPoliticsstatusService {

    @Override
    public List<Politicsstatus> getAllPoliticsstatus() {
        return baseMapper.selectList(null);
    }
}
