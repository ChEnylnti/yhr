package io.chenylnti.yhr.framework.utils;/**
 * @author chenylnti
 * @date 2024/4/14
 */

import io.chenylnti.yhr.framework.entity.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author:ChEnylnti
 * @create: 2024-04-14 13:35
 * @Description:
 */

public class HrUtils {

    public static Hr getCurrentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
