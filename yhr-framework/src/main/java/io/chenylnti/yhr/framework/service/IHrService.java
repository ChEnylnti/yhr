package io.chenylnti.yhr.framework.service;

import io.chenylnti.yhr.framework.entity.Hr;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-06
 */
public interface IHrService extends IService<Hr>, UserDetailsService {

}
