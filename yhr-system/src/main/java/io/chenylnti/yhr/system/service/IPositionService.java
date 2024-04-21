package io.chenylnti.yhr.system.service;

import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.RespPageBean;
import io.chenylnti.yhr.system.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-10
 */
public interface IPositionService extends IService<Position> {

    RespPageBean getPositionsByPage(Integer page, Integer size);

    RespBean addPosition(Position position);

    RespBean deletePositionById(Integer id);

    List<Position> getAllPositions();
}
