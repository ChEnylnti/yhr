package io.chenylnti.yhr.employee.service;

import io.chenylnti.yhr.employee.entity.Joblevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-17
 */
public interface IJoblevelService extends IService<Joblevel> {

    List<Joblevel> getAllJobLevels();

}
