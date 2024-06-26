package io.chenylnti.yhr.framework.mapper;

import io.chenylnti.yhr.framework.entity.Hr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chenylnti.yhr.framework.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-06
 */
public interface HrMapper extends BaseMapper<Hr> {

    List<Role> getHrRolesByHrId(Integer hrId);

    List<Hr> getAllHrs(@Param("hrId") Integer hrId,@Param("keywords") String keywords);

    int deleteAllHrRoles(Integer id);
}
