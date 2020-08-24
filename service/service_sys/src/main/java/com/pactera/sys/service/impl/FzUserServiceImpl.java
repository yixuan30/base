package com.pactera.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pactera.config.exceptionhandler.pacteraException;
import com.pactera.sys.entity.FzUser;
import com.pactera.sys.entity.FzUserRole;
import com.pactera.sys.entity.role.RoleVo;
import com.pactera.sys.entity.vo.UserInfo;
import com.pactera.sys.entity.vo.UserVo;
import com.pactera.sys.mapper.FzUserMapper;
import com.pactera.sys.service.FzUserRoleService;
import com.pactera.sys.service.FzUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-19
 */
@Service
public class FzUserServiceImpl extends ServiceImpl<FzUserMapper, FzUser> implements FzUserService {

    @Autowired
    private FzUserRoleService fzUserRoleService;
    /*
    * 添加用户信息
    * */
    @Override
    public String addUser(UserInfo user) {
        //封装用户信息
        FzUser fzUser = new FzUser();
        BeanUtils.copyProperties(user,fzUser);
        int res = baseMapper.insert(fzUser);
        if(res == 0){
            throw new pacteraException(2001,"添加用户信息失败");
        }
        //获取所有的角色和用户的id
        List<RoleVo> rolelist = user.getRolelist();
        String userId = fzUser.getUserId();
        //循环遍历所有的角色,并加入用户-角色中间表
        for(RoleVo role : rolelist){
            String roleId = role.getRoleId();
            FzUserRole fzUserRole = new FzUserRole();
            fzUserRole.setRoleId(roleId);
            fzUserRole.setUserId(userId);
            //插入到用户-角色表中
            boolean save = fzUserRoleService.save(fzUserRole);
            if(!save){
                throw new pacteraException(2001,"添加用户角色表失败");
            }
        }
        //返回用户id
        return userId;
    }
    /*
    * 修改用户信息
    * */
    @Override
    public int updateUser(UserInfo user) {
        //封装用户信息
        FzUser fzUser = new FzUser();
        BeanUtils.copyProperties(user,fzUser);
        int res = baseMapper.updateById(fzUser);
        if(res == 0){
            throw new pacteraException(2001,"更新用户信息失败");
        }
        //获取所有的角色和用户的id
        List<RoleVo> rolelist = user.getRolelist();
        String userId = fzUser.getUserId();
        //循环遍历所有的角色,并加入用户-角色中间表
        for(RoleVo role : rolelist){
            String roleId = role.getRoleId();
            FzUserRole fzUserRole = new FzUserRole();
            fzUserRole.setRoleId(roleId);
            fzUserRole.setUserId(userId);
            //插入到用户-角色表中
             fzUserRoleService.updateById(fzUserRole);

        }
        //返回用户id
        return 1;
    }
    /*
    * 根据id查询用户信息
    * */
    @Override
    public UserInfo getUserById(String id) {
        UserInfo userInfo = new UserInfo();
        //获取用户信息
        FzUser fzUser = this.getById(id);
        if(fzUser != null) {
            BeanUtils.copyProperties(fzUser, userInfo);
        }

        QueryWrapper<FzUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        //        //根据id所有的用户-角色信息
        List<FzUserRole> listUserRole = fzUserRoleService.list(queryWrapper);
        List<RoleVo> listRole = new ArrayList<>();
        //封装角色信息
        for(FzUserRole userRole : listUserRole){
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(userRole,roleVo);
            if(roleVo != null){
                listRole.add(roleVo);
            }
        }
        userInfo.setRolelist(listRole);
        return userInfo;
    }
    /*
    * 删除用户信息
    * */
    @Override
    public void delUserByid(String id) {
        QueryWrapper<FzUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        //根据id所有的用户-角色信息
        boolean res = fzUserRoleService.remove(queryWrapper);
        if(!res){
            throw new pacteraException(2001,"删除用户-角色信息失败");
        }
        boolean b = this.removeById(id);
        if(!b){
            throw new pacteraException(2001,"删除用户信息失败");
        }
    }
    /*
    * 分页查询用户信息
    * */
    @Override
    public Map<String, Object> pageListWeb(Page<FzUser> pageParam, UserVo userVo) {
        QueryWrapper<FzUser> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userVo.getPhone())){
            queryWrapper.eq("phone",userVo.getPhone());
        }
        if(!StringUtils.isEmpty(userVo.getSex())){
            queryWrapper.eq("sex",userVo.getSex());
        }
        if(!StringUtils.isEmpty(userVo.getState())){
            queryWrapper.eq("state",userVo.getState());
        }
        if(!StringUtils.isEmpty(userVo.getUserName())){
            queryWrapper.eq("user_name",userVo.getUserName());
        }
        baseMapper.selectPage(pageParam,queryWrapper);
        List<FzUser> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
    /*
    * 给用分配角色
    * */
    @Override
    public void assignRoles(String id, Long[] ids) {

    }


}

