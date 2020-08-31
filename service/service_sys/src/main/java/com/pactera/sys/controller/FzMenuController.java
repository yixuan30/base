package com.pactera.sys.controller;


import com.pactera.commonUtils.R;
import com.pactera.sys.entity.FzMenu;
import com.pactera.sys.entity.menu.MenuNodeVO;
import com.pactera.sys.entity.menu.MenuVO;
import com.pactera.sys.service.FzMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-21
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单权限接口")
@CrossOrigin
public class FzMenuController {

    @Autowired
    private FzMenuService menuService;
    /*
    *加载菜单树
     */
    @ApiOperation("加载菜单树")
    @GetMapping("/tree")
    public R tree(){
        List<MenuNodeVO> menuTree = menuService.findMenuTree();
        List<String> ids = menuService.findOpenIds();
        Map<String,Object> map = new HashMap<>();
        map.put("tree",menuTree);
        map.put("open",ids);
        return R.ok().data(map);
    }

    /*
    * 新增菜单和按钮
    * */
    @ApiOperation("新增菜单和按钮")
    @PostMapping("/add")
    @RequiresPermissions({"menu:add"})
    public R add(@RequestBody MenuVO menuVO){
        FzMenu node = menuService.add(menuVO);
        Map<String,Object> map = new HashMap<>();
        map.put("id",node.getMenuId());
        map.put("menuName",node.getMenuName());
        map.put("children",new ArrayList<>());
        map.put("icon",node.getIcon());
        return  R.ok().data(map);
    }

    /*
    *删除菜单
    * */
    @ApiOperation("删除菜单")
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions({"menu:delete"})
    public R delete(@PathVariable String id){
        menuService.delete(id);
        return R.ok();
    }
    /*
    * 更新菜单
    * */
    @ApiOperation("更新菜单")
    @PutMapping("/update/{id}")
    @RequiresPermissions({"menu:update"})
    public  R update(@PathVariable String id,@RequestBody MenuVO  menuVO){
        menuService.updateMenu(id,menuVO);
        return R.ok();
    }
    /*
    * 获取菜单详情
    * */
    @ApiOperation("菜单详情")
    @GetMapping("/edit/{id}")
    @RequiresPermissions({"menu:edit"})
    public  R edit(@PathVariable String id){
        MenuVO menuVO = menuService.edit(id);
        return R.ok().data("menuVo",menuVO);
    }
}

