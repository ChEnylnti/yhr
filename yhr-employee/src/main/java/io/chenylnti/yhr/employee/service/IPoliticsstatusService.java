package io.chenylnti.yhr.employee.service;

import io.chenylnti.yhr.employee.entity.Politicsstatus;
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
public interface IPoliticsstatusService extends IService<Politicsstatus> {

    List<Politicsstatus> getAllPoliticsstatus();
}
