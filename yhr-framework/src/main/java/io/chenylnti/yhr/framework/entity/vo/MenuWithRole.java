package io.chenylnti.yhr.framework.entity.vo;/**
 * @author chenylnti
 * @date 2024/4/10
 */

import io.chenylnti.yhr.framework.entity.Menu;
import io.chenylnti.yhr.framework.entity.Role;

import java.util.List;

/**
 * @author:ChEnylnti
 * @create: 2024-04-10 15:49
 * @Description:
 */

public class MenuWithRole extends Menu {
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
