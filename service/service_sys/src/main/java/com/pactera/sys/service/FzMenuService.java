package com.pactera.sys.service;

import com.pactera.sys.entity.FzMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pactera.sys.entity.menu.MenuNodeVO;
import com.pactera.sys.entity.menu.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-21
 */
public interface FzMenuService extends IService<FzMenu> {
    /*
    * 获取菜单树
    * */
    List<MenuNodeVO> findMenuTree();

    /*
    * 所有展开菜单的Id
    * */

    List<String> findOpenIds();
    /*
    * 添加菜单
    * */
    FzMenu add(MenuVO menuVO);
    /*
    * 删除菜单
    * */
    void delete(String id);
    /*
    * 更新菜单
    * */
    void updateMenu(String id, MenuVO menuVO);

    List<FzMenu> selectAll();

    MenuVO edit(String id);
}
