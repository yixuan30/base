package com.pactera.sys.controller;


import com.pactera.commonUtils.R;
import com.pactera.sys.converter.RoleConverter;
import com.pactera.sys.entity.FzRole;
import com.pactera.sys.entity.menu.MenuNodeVO;
import com.pactera.sys.entity.page.PageVo;
import com.pactera.sys.entity.role.RoleTransferItemVo;
import com.pactera.sys.entity.user.UserEditVo;
import com.pactera.sys.entity.user.UserInfoVo;
import com.pactera.sys.entity.user.UserQuery;
import com.pactera.sys.entity.vo.UserInfo;
import com.pactera.sys.entity.vo.UserVo;
import com.pactera.sys.service.FzRoleService;
import com.pactera.sys.service.FzUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/user")
@Api(description = "用户管理")
@CrossOrigin
public class FzUserController {

    @Autowired
    private FzUserService userService;

    @Autowired
    private FzRoleService roleService;

    /*
    * 用户列表
    * */
    @ApiOperation("用户列表")
    @GetMapping("/findUserList")
    public  R findUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize,
                           UserVo userVo){
        PageVo<UserVo> userList = userService.findUserList(pageNum,pageSize,userVo);
        return R.ok().data("userList",userList);

    }

    /*
     * 添加用户
     * */
    @ApiOperation("添加用户")
    @PostMapping("add")
    @RequiresPermissions({"user:add"})
    public R addUser(@RequestBody UserVo user){
        userService.addUser(user);
       return R.ok();
    }
    /*
    * 根据id查询用户信息
    * */
    @ApiOperation("根据id查询用户信息")
    @GetMapping("getUserById/{id}")
    public R getUserById(@PathVariable String id){
        UserInfo userInfo = userService.getUserById(id);
        return R.ok().data("userInfo",userInfo);
    }

    /*
    * 更新用户状态
    * */
    @ApiOperation("更新用户状态")
    @PutMapping("/updateStatus/{id}/{status}")
    @RequiresPermissions({"user:status"})
    public  R udpateStatus(@PathVariable String id,@PathVariable Boolean status){
        userService.updateStatus(id,status);
        return R.ok();
    }
    /*
    *编辑用户
    * */
    @ApiOperation("编辑用户")
    @GetMapping("/edit/{id}")
    @RequiresPermissions({"user:edit"})
    public R edit(@PathVariable String id){
        UserEditVo userEditVo = userService.edit(id);
        return  R.ok().data("userVO",userEditVo);
    }
    /*
    * 更新用户
    * */
    @ApiOperation("更新用户")
    @PutMapping("/update/{id}")
    @RequiresPermissions({"user:update"})
    public R update1(@PathVariable String id,@RequestBody UserEditVo userEditVo){
        userService.update1(id,userEditVo);
        return R.ok();
    }
    /*
    * 删除用户
    * */
    @ApiOperation("删除用户信息")
    @DeleteMapping("delete/{id}")
    @RequiresPermissions({"user:delete"})
    public R delete(@PathVariable String  id){
        userService.delUserByid(id);
        return R.ok();
    }
    /*
    * 分配角色
    * */
    @ApiOperation("分配角色")
    @PostMapping("{id}/assignRoles")
    @RequiresPermissions({"user:assign"})
    public R assignRole(@PathVariable String id ,@RequestBody String[] ids){
        userService.assignRoles(id,ids);
        return  R.ok();
    }

    /*
    * 用户登录
    * */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login(@RequestBody(required = true) UserQuery userQuery,
                   HttpServletRequest  request){
        String token   = userService.login(userQuery);
        return R.ok().data("token",token);
    }

    /*
    * 用户信息
    * */
    @ApiOperation("用户信息")
    @GetMapping("/info")
    public R info(){
        UserInfoVo userInfoVo =userService.info();
        return  R.ok().data("userInfo",userInfoVo);
    }

    /*
    * 加载菜单
    * */
    @GetMapping("/findMenu")
    @ApiOperation("获取菜单")
    public R findMenu(){
        List<MenuNodeVO>  menuNodeVOS = userService.findMenu();
        return R.ok().data("menu",menuNodeVOS);
    }
    /*
    * 获取用户已有的角色
    * */
    @ApiOperation("获取用户已有的角色")
    @GetMapping("/{id}/roles")
    public R roles(@PathVariable String id){
        List<String> values = userService.roles(id);
        List<FzRole> list =  roleService.findAll();
        //转换为前端需要的角色Item
        List<RoleTransferItemVo> items = RoleConverter.converterToRoleTransferItem(list);
        Map<String,Object> map = new HashMap<>();
        map.put("roles",items);
        map.put("values",values);
        return R.ok().data(map);
    }
}

