package io.chenylnti.yhr.employee.service.impl;

import io.chenylnti.yhr.employee.entity.Joblevel;
import io.chenylnti.yhr.employee.mapper.JoblevelMapper;
import io.chenylnti.yhr.employee.service.IJoblevelService;
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
public class JoblevelServiceImpl extends ServiceImpl<JoblevelMapper, Joblevel> implements IJoblevelService {

    @Override
    public List<Joblevel> getAllJobLevels() {
        return baseMapper.selectList(null);
    }
}
