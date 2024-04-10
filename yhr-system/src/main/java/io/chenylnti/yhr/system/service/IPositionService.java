package io.chenylnti.yhr.system.service;

import io.chenylnti.yhr.framework.entity.RespPageBean;
import io.chenylnti.yhr.system.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
