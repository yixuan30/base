package com.pactera.sys.utils;

import com.pactera.sys.entity.menu.MenuNodeVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuTreeBuilder {
        public static List<MenuNodeVO> build(List<MenuNodeVO> nodes){
            List<MenuNodeVO> rootNode = new ArrayList<>();
            //先选出根级菜单
            for(MenuNodeVO node : nodes){
                if(node.getParentId().equals("0")){
                    rootNode.add(node);
                }
            }
            Collections.sort(rootNode,MenuNodeVO.order());
            //为根级菜单添加子菜单
            for(MenuNodeVO  node : rootNode ){
                List<MenuNodeVO>  childNode =getChild(node.getMenuId(),nodes);
                node.setChildern(childNode);
            }
            return  rootNode;
        }

    private static List<MenuNodeVO> getChild(String menuId, List<MenuNodeVO> nodes) {
            List<MenuNodeVO> childNode = new ArrayList<>();
            //根据传过来的id和当前节点的父节点判断是否加入
        for(MenuNodeVO node : nodes){
            if(node.getParentId().equals(menuId)){
                childNode.add(node);
            }
        }
        //递归添加子菜单
        for(MenuNodeVO node : childNode){
            node.setChildern(getChild(node.getMenuId(),nodes));
        }
        Collections.sort(childNode,MenuNodeVO.order());
        if(childNode.size() ==0){
            return  new ArrayList<MenuNodeVO>();
        }
        return childNode;
    }

}
