package com.pactera.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pactera.commonUtils.JWTUtils;
import com.pactera.commonUtils.MD5Utils;
import com.pactera.config.exceptionhandler.pacteraException;
import com.pactera.sys.bean.ActiveUser;
import com.pactera.sys.config.jwt.JWTToken;
import com.pactera.sys.converter.MenuConverter;
import com.pactera.sys.converter.UserConverter;
import com.pactera.sys.entity.*;
import com.pactera.sys.entity.enums.UserStatusEnum;
import com.pactera.sys.entity.enums.UserTypeEnum;
import com.pactera.sys.entity.menu.MenuNodeVO;
import com.pactera.sys.entity.page.PageVo;
import com.pactera.sys.entity.role.RoleVo;
import com.pactera.sys.entity.user.UserEditVo;
import com.pactera.sys.entity.user.UserInfoVo;
import com.pactera.sys.entity.user.UserQuery;
import com.pactera.sys.entity.vo.UserInfo;
import com.pactera.sys.entity.vo.UserVo;
import com.pactera.sys.mapper.FzUserMapper;
import com.pactera.sys.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pactera.sys.utils.MenuTreeBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private FzRoleService fzRoleService;

    @Autowired
    private FzRoleMenuService fzRoleMenuService;

    @Autowired
    private FzMenuService fzMenuService;

    @Autowired
    private UserConverter userConverter;
    /*
    * 添加用户信息
    * */
    @Override
    public void addUser(UserVo user) {
        //封装用户信息
        FzUser fzUser = new FzUser();
        BeanUtils.copyProperties(user,fzUser);
        String salt=UUID.randomUUID().toString().substring(0,32);
        fzUser.setPassword(MD5Utils.md5Encryption(user.getPassword(), salt));
        fzUser.setSalt(salt);
        fzUser.setType(UserTypeEnum.SYSTEM_USER.getTypeCode());//添加的用户默认是普通用户
        fzUser.setState(UserStatusEnum.AVAILABLE.getStatusCode());//添加的用户默认启用
        fzUser.setAvatar("https://education-1010.oss-cn-beijing.aliyuncs.com/logo.jpg");
        baseMapper.insert(fzUser);


    }

    /*
    * 修改用户状态
    * */
    @Override
    public void updateStatus(String id, Boolean status) {
        FzUser fzUser = baseMapper.selectById(id);
        if(fzUser == null){
            throw new pacteraException(2001,"用户不存在");
        }
        ActiveUser activeUser = (ActiveUser)SecurityUtils.getSubject().getPrincipal();
        if(fzUser.getUserId().equals(activeUser.getUser().getUserId())){
            throw new pacteraException(2001,"不予许禁用当前用户");
        }else {
            FzUser u = new FzUser();
            u.setUserId(id);
            u.setState(status ? UserStatusEnum.DISABLE.getStatusCode() : UserStatusEnum.AVAILABLE.getStatusCode());
            baseMapper.updateById(u);
        }
    }
    /*
    * 编辑用户
    * */
    @Override
    public UserEditVo edit(String id) {
        FzUser user = baseMapper.selectById(id);
        if(user ==null){
            throw new pacteraException(2001,"用户不错在");
        }
        UserEditVo userEditVo = new UserEditVo();
        BeanUtils.copyProperties(user,userEditVo);
        return userEditVo;
    }
    /*
    * 更新用户
    * */
    @Override
    public void update1(String id, UserEditVo userEditVo) {
        FzUser user = baseMapper.selectById(id);
        String username = userEditVo.getUserName();
        if(user ==null){
            throw new pacteraException(2001,"用户不错在");
        }
        QueryWrapper<FzUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        Integer res = baseMapper.selectCount(queryWrapper);
        if(res==0){
            throw new pacteraException(2001,"用户名已被占用");
        }
        FzUser user1 = new FzUser();
        BeanUtils.copyProperties(userEditVo,user1);
        user1.setUserId(user.getUserId());
        baseMapper.updateById(user1);
    }
    /*
    * 用户列表
    * */
    @Override
    public PageVo<UserVo> findUserList(Integer pageNum, Integer pageSize, UserVo userVo) {
        PageHelper.startPage(pageNum,pageSize);
        String username = userVo.getUserName();
        String nickname = userVo.getNickName();
        Integer sex = userVo.getSex();
        String email = userVo.getEmail();
        QueryWrapper<FzUser> queryWrapper = new QueryWrapper<>();
        if(username!=null&&!"".equals(username)){
            queryWrapper.like("user_name",username);
        }
        if(nickname!=null&&!"".equals(nickname)){
            queryWrapper.like("nick_name","nickname");
        }
        if(email!=null&&!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if(sex!=null){
            queryWrapper.like("sex",sex);
        }
        queryWrapper.eq("type",0);
        List<FzUser> fzUsers = baseMapper.selectList(queryWrapper);
        List<UserVo> userVos = userConverter.converterToUserVOList(fzUsers);
        PageInfo<FzUser> info = new PageInfo<>(fzUsers);
        return new PageVo<>(info.getTotal(),userVos);
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
        FzUser user = baseMapper.selectById(id);
        if(user==null){
            throw new pacteraException(2001,"要删除的用户不存在");
        }
        ActiveUser activeUser = (ActiveUser)SecurityUtils.getSubject().getPrincipal();
        if(activeUser.getUser().getUserId().equals(user.getUserId())){
            throw new pacteraException(2001,"不能删除当前登录的用户");
        }
        baseMapper.deleteById(id);
        //删除对应的角色
        QueryWrapper<FzUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        //根据id所有的用户-角色信息
        fzUserRoleService.remove(queryWrapper);
    }
    /*
    * 给用分配角色
    * */
    @Override
    public void assignRoles(String id, String[] ids) {
        FzUser fzUser = baseMapper.selectById(id);
        if(fzUser==null){
            throw new pacteraException(2001,"用户不存在");
        }
        //删除之前的分配
        QueryWrapper<FzUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        fzUserRoleService.remove(queryWrapper);
        //添加现在的分配
        if(ids.length>0){
                for(String rid : ids){
                    FzRole role = fzRoleService.getById(rid);
                    if(role == null){
                        throw new pacteraException(2001,"该角色不存在");
                    }
                    if(role.getState().equals("0")){
                        throw new pacteraException(2001,"该用户已被禁用");
                    }
                    FzUserRole fzUserRole = new FzUserRole();
                    fzUserRole.setUserId(id);
                    fzUserRole.setRoleId(rid);
                    fzUserRoleService.save(fzUserRole);
                }
        }

    }
    /*
    * 用户登录
    * */
    @Override
    public String login(UserQuery userQuery) {
        String token;
        String name = userQuery.getUsername();
        String password = userQuery.getPassword();
        FzUser user = findUserByName(name);
        if(user!=null){
            String salt =user.getSalt();
            //秘钥为盐
            String target = MD5Utils.md5Encryption(password, salt);
            //生成token
            token = JWTUtils.sign(name, target);
            JWTToken jwtToken = new JWTToken(token);
            try {
                SecurityUtils.getSubject().login(jwtToken);
            } catch (AuthenticationException e) {
                throw new pacteraException(2001,e.getMessage());
            }
        }else {
            throw new pacteraException(2001,"用户未找到");
        }
        return token;
    }
    /*
    * 根据用户名查询用户
    * */
    public FzUser findUserByName(String name) {
        QueryWrapper<FzUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",name);
        FzUser one = this.getOne(queryWrapper);
        return one;
    }
    /*
    * 根据id获取角色
    * */
    @Override
    public List<FzRole> findRolesById(String userId) {
        QueryWrapper<FzUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<FzUserRole> URlist = fzUserRoleService.list(queryWrapper);
        List<String> ids = new ArrayList<>();
        List<FzRole> roles = new ArrayList<>();
        if(!CollectionUtils.isEmpty(URlist)){
            //获取ids
            for (FzUserRole userRole : URlist){
                ids.add(userRole.getRoleId());
            }
            //获取roles
            if(!CollectionUtils.isEmpty(ids)){
                for(String id : ids){
                    FzRole role = fzRoleService.getById(id);
                    roles.add(role);
                }
            }
        }
        return roles;
    }
    /*
    *根据角色获取菜单
    * */
    @Override
    public List<FzMenu> findMenuByRoles(List<FzRole> roles) {
        List<FzMenu> menus = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roles)){
            //使用set集合去重
            Set<String> menuIds = new HashSet<>();
            List<FzRoleMenu> roleMenus;
            //获取roleMenus
            for(FzRole role : roles){
                QueryWrapper<FzRoleMenu> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("role_id",role.getRoleId());
                roleMenus = fzRoleMenuService.list(queryWrapper);
                //获取menuIds
                if(!CollectionUtils.isEmpty(roleMenus)){
                    for(FzRoleMenu roleMenu: roleMenus){
                        menuIds.add(roleMenu.getMenuId());
                    }
                }
                //获取menus
                if(!CollectionUtils.isEmpty(menuIds)){
                    for (String menuId: menuIds){
                        FzMenu menu = fzMenuService.getById(menuId);
                        menus.add(menu);
                    }
                }
            }
        }
        return menus;
    }
    /*
    * 获取用户信息
    * */
    @Override
    public UserInfoVo info() {
        ActiveUser activeUser  = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setAvatar(activeUser.getUser().getAvatar());
        userInfoVo.setUserName(activeUser.getUser().getUserName());
        userInfoVo.setNickName(activeUser.getUser().getNickName());
        userInfoVo.setUrl(activeUser.getUrls());
        List<String> roleNames = activeUser.getRoles().stream().map(FzRole::getRoleName).collect(Collectors.toList());
        userInfoVo.setRoles(roleNames);
        userInfoVo.setPerms(activeUser.getPermissions());
        userInfoVo.setIsAdmin(activeUser.getUser().getType()== UserTypeEnum.SYSTEM_ADMIN.getTypeCode());
        return userInfoVo;
    }
    /*
    * 获取菜单
    * */
    @Override
    public List<MenuNodeVO> findMenu() {
        List<FzMenu> menus =null;
        List<MenuNodeVO> menuNodeVOS = new ArrayList<>();
        ActiveUser activeUser = (ActiveUser)SecurityUtils.getSubject().getPrincipal();
        if(activeUser.getUser().getType()==UserTypeEnum.SYSTEM_ADMIN.getTypeCode()){
            //超级管理员
            menus = fzMenuService.selectAll();
        }else if(activeUser.getUser().getType() == UserTypeEnum.SYSTEM_USER.getTypeCode()){
            //普通用户
            menus = activeUser.getMenus();
        }
        if(!CollectionUtils.isEmpty(menus)){
            menuNodeVOS = MenuConverter.converterToMenuNodeVO(menus);
        }
        return MenuTreeBuilder.build(menuNodeVOS);
    }
    /*
    * 用户拥有的角色Ids
    * */
    @Override
    public List<String> roles(String id) {
        List<String> roleIds = new ArrayList<>();
        QueryWrapper<FzUserRole> queryWrapper = new QueryWrapper<>();
        List<FzUserRole> lists = fzUserRoleService.list(queryWrapper);
        if(!CollectionUtils.isEmpty(lists)){
            for(FzUserRole list : lists){
                FzRole role = fzRoleService.getById(list.getRoleId());
                if(role != null){
                    roleIds.add(role.getRoleId());
                }
            }
        }
        return roleIds;
    }




}

