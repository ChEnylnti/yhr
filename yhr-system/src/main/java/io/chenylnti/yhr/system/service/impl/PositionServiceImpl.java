package io.chenylnti.yhr.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.chenylnti.yhr.framework.entity.RespPageBean;
import io.chenylnti.yhr.system.entity.Position;
import io.chenylnti.yhr.system.mapper.PositionMapper;
import io.chenylnti.yhr.system.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-10
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

    @Override
    public RespPageBean getPositionsByPage(Integer page, Integer size) {
        Page<Position> result = page(new Page<>(page, size));
        List<Position> records = result.getRecords();
        long total = result.getTotal();
        return new RespPageBean(total,records);
    }
}
