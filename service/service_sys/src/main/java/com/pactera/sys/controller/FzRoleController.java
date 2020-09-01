package com.pactera.sys.controller;


import com.pactera.commonUtils.R;
import com.pactera.sys.entity.FzRole;
import com.pactera.sys.entity.menu.MenuNodeVO;
import com.pactera.sys.entity.page.PageVo;
import com.pactera.sys.entity.role.RoleVo;
import com.pactera.sys.service.FzMenuService;
import com.pactera.sys.service.FzRoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/role")
public class FzRoleController {
    @Autowired
    private FzRoleService fzRoleService;
    @Autowired
    private FzMenuService fzMenuService;

    /*
    * 根据角色id授权
    * */
    @ApiOperation("角色授权")
    @PostMapping("/authority/{id}")
    @RequiresPermissions({"role:authority"})
    public R authority(@PathVariable String id ,  @RequestBody  String[] mids){
        fzRoleService.authority(id,mids);
        return R.ok();
    }
    /*
    *角色菜单
    * */
    @ApiOperation("角色菜单")
    @GetMapping("/findByMenu/{id}")
    public R findRoleMenu(@PathVariable String id){
        List<MenuNodeVO> menuTree = fzMenuService.findMenuTree();
        //角色拥有的菜单id
        List<String> mids = fzRoleService.findMenuIdsByRoleId(id);
        List<String> ids = fzMenuService.findOpenIds();
        Map<String,Object> map = new HashMap<>();
        map.put("tree",menuTree);
        map.put("mids",mids);
        map.put("open",ids);
        return R.ok().data(map);
    }
    /*
    * 添加角色
    * */
    @ApiOperation(value = "添加角色")
    @PostMapping("/add")
    @RequiresPermissions({"role:add"})
    public R add(@RequestBody FzRole role) {
        fzRoleService.add(role);
        return R.ok();
    }
    /*
    * 删除角色
    * */
    @ApiOperation(value = "删除角色", notes = "根据id删除角色信息")
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions({"role:delete"})
    public R delete(@PathVariable String id) {
        fzRoleService.deleteById(id);
        return R.ok();
    }
    /*
    * 更新角色
    * */
    @ApiOperation(value = "更新角色", notes = "根据id更新角色信息")
    @PutMapping("/update/{id}")
    public R update(@PathVariable String id, @RequestBody  FzRole role) {
        fzRoleService.upd(id, role);
        return R.ok();
    }
    /*
    * 角色列表
    * */
    @ApiOperation(value = "角色列表")
    @GetMapping("/findRoleList")
    public R findRoleList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "7")Integer pageSize,
                          RoleVo roleVo){
        PageVo<RoleVo> roleList = fzRoleService.findRoleList(pageNum,pageSize,roleVo);
        return  R.ok().data("roleList",roleList);
    }
    /*
    *编辑角色信息
    * */
    @ApiOperation(value = "编辑角色信息")
    @GetMapping("/edit/{id}")
    @RequiresPermissions({"role:edit"})
    public  R edit(@PathVariable String id){
        RoleVo roleVo = fzRoleService.edit(id);
        return R.ok().data("roleVo",roleVo);
    }
    /*
    * 更新角色状态
    * */
    @ApiOperation(value = "更新角色状态")
    @PutMapping("/updateStatus/{id}/{state}")
    @RequiresPermissions({"role:status"})
    public  R updateStatus(@PathVariable String id,@PathVariable  Boolean state){
        fzRoleService.updateStatus(id,state);
        return  R.ok();
    }

}

