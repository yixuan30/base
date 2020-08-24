package com.pactera.sys.converter;

import com.pactera.sys.entity.FzMenu;
import com.pactera.sys.entity.menu.MenuNodeVO;
import com.pactera.sys.entity.menu.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuConverter {

    /**
     * 转成menuVO(只包含菜单)List
     */
    public static List<MenuNodeVO> converterToMenuNodeVO(List<FzMenu> menus){
        //先过滤出用户的菜单
        List<MenuNodeVO> menuNodeVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(menus)){
            for (FzMenu menu : menus) {
                if(menu.getType()==0){
                    MenuNodeVO menuNodeVO = new MenuNodeVO();
                    BeanUtils.copyProperties(menu,menuNodeVO);
                    menuNodeVO.setDisabled(menu.getAvailable()==0);
                    menuNodeVOS.add(menuNodeVO);
                }
            }
        }
        return menuNodeVOS;
    }


    /**
     * 转成menuVO(菜单和按钮）
     */
    public static List<MenuNodeVO> converterToALLMenuNodeVO(List<FzMenu> menus){
        //先过滤出用户的菜单
        List<MenuNodeVO> menuNodeVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(menus)){
            for (FzMenu menu : menus) {
                MenuNodeVO menuNodeVO = new MenuNodeVO();
                BeanUtils.copyProperties(menu,menuNodeVO);
                menuNodeVO.setDisabled(menu.getAvailable()==0);
                menuNodeVOS.add(menuNodeVO);
            }
        }
        return menuNodeVOS;
    }
    /**
     * 转成menuVO(菜单和按钮）
     */
    public static MenuVO converterToMenuVO(FzMenu menu){
        MenuVO menuVO = new MenuVO();
        if(menu!=null){
            BeanUtils.copyProperties(menu,menuVO);
            menuVO.setDisabled(menu.getAvailable()==0);
        }
        return menuVO;
    }

}
