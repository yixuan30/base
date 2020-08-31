package com.pactera.sys.service.impl;

import com.pactera.config.exceptionhandler.pacteraException;
import com.pactera.sys.converter.MenuConverter;
import com.pactera.sys.entity.FzMenu;
import com.pactera.sys.entity.menu.MenuNodeVO;
import com.pactera.sys.entity.menu.MenuVO;
import com.pactera.sys.mapper.FzMenuMapper;
import com.pactera.sys.service.FzMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pactera.sys.utils.MenuTreeBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-21
 */
@Service
public class FzMenuServiceImpl extends ServiceImpl<FzMenuMapper, FzMenu> implements FzMenuService {


    /*
    * 获取菜单树
    * */
    @Override
    public List<MenuNodeVO> findMenuTree() {
        List<FzMenu> menus = baseMapper.selectAll();
        List<MenuNodeVO> menuNodeVOS = MenuConverter.converterToALLMenuNodeVO(menus);
        return  MenuTreeBuilder.build(menuNodeVOS);
    }

    /*
    * 获取所有的展开菜单Id
    * */
    @Override
    public List<String> findOpenIds() {
        List<String> ids = new ArrayList<>();
        List<FzMenu> menus = baseMapper.selectAll();
        if(CollectionUtils.isEmpty(menus)){
            for(FzMenu menu : menus){
                if(menu.getOpen() == 1){
                    ids.add(menu.getMenuId());
                }
            }
        }
        return ids;
    }
    /*
    * 添加菜单
    * */
    @Override
    public FzMenu add(MenuVO menuVO) {
        FzMenu menu = new FzMenu();
        BeanUtils.copyProperties(menuVO,menu);
        menu.setAvailable(menuVO.isDisabled()?0:1);
        baseMapper.insert(menu);
        return menu;
    }
    /*
    * 删除菜单
    * */
    @Override
    public void delete(String id) {
        FzMenu menu = baseMapper.selectById(id);
        if(menu == null){
            throw new pacteraException(2001,"要删除的菜单不存在");
        }
        baseMapper.deleteById(id);
    }
    /*
     * 更新菜单
     * */
    @Override
    public void updateMenu(String id, MenuVO menuVO) {
        FzMenu menu = baseMapper.selectById(id);
        if(menu ==null){
            throw new pacteraException(2001,"要更新的菜单不存在");
        }
        FzMenu fzMenu = new FzMenu();
        BeanUtils.copyProperties(menuVO,fzMenu);
        fzMenu.setMenuId(id);
        fzMenu.setAvailable(menuVO.isDisabled()?1:0);
        baseMapper.updateById(fzMenu);

    }
    /*
    * 获取所有的的菜单
    * */
    @Override
    public List<FzMenu> selectAll() {
        return baseMapper.selectAll();
    }
    /*
    * 获取菜单详情
    * */
    @Override
    public MenuVO edit(String id) {
        FzMenu menu = baseMapper.selectById(id);
        if(menu == null){
            throw new pacteraException(2001,"该编辑的菜单不存在");
        }
        return MenuConverter.converterToMenuVO(menu);
    }


}
