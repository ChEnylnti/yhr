package io.chenylnti.yhr.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.RespPageBean;
import io.chenylnti.yhr.system.entity.Position;
import io.chenylnti.yhr.system.mapper.PositionMapper;
import io.chenylnti.yhr.system.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public RespBean addPosition(Position position) {
        QueryWrapper<Position> qw = new QueryWrapper<>();
        qw.lambda().eq(Position::getName,position.getName());
        Position one = getOne(qw);
        if (one == null) {
            //没查到，说明没有重复名字
            position.setEnabled(true);
            position.setCreateDate(LocalDateTime.now());
            return save(position)?RespBean.ok("添加成功"):RespBean.error("添加失败");
        }else {
            return RespBean.error("职位名重复，添加失败");
        }

    }

    @Override
    public RespBean deletePositionById(Integer id) {
        Position one = getById(id);
        if (one == null) {
            //说明要删除的数据不存在
            return RespBean.error("数据不存在，删除失败");
        }
        return removeById(id)?RespBean.ok("删除成功"):RespBean.error("删除失败");
    }

    @Override
    public List<Position> getAllPositions() {
        return baseMapper.selectList(null);
    }
}
