package com.pactera.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pactera.commonUtils.R;
import com.pactera.sys.entity.FzUser;
import com.pactera.sys.entity.vo.UserInfo;
import com.pactera.sys.entity.vo.UserVo;
import com.pactera.sys.service.FzUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/sys/fz-user")
@Api(description = "用户管理")
@CrossOrigin
public class FzUserController {
    @Autowired
    private FzUserService userService;
    /*
     * 用户列表
     * */
    @ApiOperation("用户分页列表")
    @PostMapping("getUserList/{page}/{limit}")
    public R getUserList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userVo", value = "查询对象", required = false)
            @RequestBody(required = false) UserVo userVo){
        Page<FzUser> pageParam = new Page<>(page,limit);
        Map<String, Object> map = userService.pageListWeb(pageParam, userVo);
        return R.ok().data(map);
    }
    /*
     * 添加用户
     * */
    @ApiOperation("添加用户")
    @PostMapping("addUser")
    public R addUser(@RequestBody UserInfo user){
        String userId = userService.addUser(user);
        if(!StringUtils.isEmpty(userId)){
            return R.ok().data("userId",userId);
        }else {
            return R.error().message("添加用户失败");
        }
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
    * 修改用户
    * */
    @ApiOperation("修改用户信息")
    @PutMapping("updateUser")
    public  R updateuser(@RequestBody UserInfo user){
        int  res = userService.updateUser(user);
        if(res==0){
            return R.error().message("更新用户信息失败");
        }else {
            return R.ok();
        }
    }
    /*
    * 删除用户
    * */
    @ApiOperation("删除用户信息")
    @DeleteMapping("delUserByid/{id}")
    public R delUser(@PathVariable String  id){
        userService.delUserByid(id);
        return R.ok();
    }
    /*
    * 分配角色
    * */
    @ApiOperation("分配角色")
    @PostMapping("{id}/assignRoles")
    public R assignRole(@PathVariable String id ,@RequestBody Long[] ids){
        userService.assignRoles(id,ids);
        return  R.ok();
    }
}

