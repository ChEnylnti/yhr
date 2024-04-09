package io.chenylnti.yhr.framework.entity.vo;/**
 * @author chenylnti
 * @date 2024/4/9
 */

import io.chenylnti.yhr.framework.entity.Menu;

import java.util.List;

/**
 * @author:ChEnylnti
 * @create: 2024-04-09 17:08
 * @Description:
 */

public class MenuVo extends Menu {
    private List<MenuVo> children;

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }
}
