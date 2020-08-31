package com.pactera.sys.config.shiro;

import com.pactera.commonUtils.JWTUtils;
import com.pactera.sys.bean.ActiveUser;
import com.pactera.sys.entity.FzMenu;
import com.pactera.sys.entity.FzRole;
import com.pactera.sys.entity.FzUser;
import com.pactera.sys.service.FzUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private FzUserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof com.pactera.sys.config.jwt.JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        if(activeUser.getUser().getType()==0){
            authorizationInfo.addStringPermission("*:*");
        }else {
            List<String> permissions = new ArrayList<>(activeUser.getPermissions());
            List<FzRole> roleList = activeUser.getRoles();
            //授权角色
            if (!CollectionUtils.isEmpty(roleList)) {
                for (FzRole role : roleList) {
                    authorizationInfo.addRole(role.getRoleName());
                }
            }
            //授权权限
            if (!CollectionUtils.isEmpty(permissions)) {
                for (String  permission : permissions) {
                    if (permission != null && !"".equals(permission)) {
                        authorizationInfo.addStringPermission(permission);
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtils.getUsername(token);

        if (username == null) {
            throw new AuthenticationException(" token错误，请重新登入！");
        }

        FzUser userBean = userService.findUserByName(username);

        if (userBean == null) {
            throw new AccountException("账号不存在!");
        }
        if(JWTUtils.isExpire(token)){
            throw new AuthenticationException(" token过期，请重新登入！");
        }

        if (! JWTUtils.verify(token, username, userBean.getPassword())) {
            throw new CredentialsException("密码错误!");
        }

        if(userBean.getState().equals("0")){
            throw new LockedAccountException("账号已被锁定!");
        }

        //如果验证通过，获取用户的角色
        List<FzRole> roles= userService.findRolesById(userBean.getUserId());
        //查询用户的所有菜单(包括了菜单和按钮)
        List<FzMenu> menus=userService.findMenuByRoles(roles);

        Set<String> urls=new HashSet<>();
        Set<String> perms=new HashSet<>();
        if(!CollectionUtils.isEmpty(menus)){
            for (FzMenu menu : menus) {
                String url = menu.getUrl();
                String per = menu.getPerms();
                if(menu.getType()==0&& !StringUtils.isEmpty(url)){
                    urls.add(menu.getUrl());
                }
                if(menu.getType()==1&&!StringUtils.isEmpty(per)){
                    perms.add(menu.getPerms());
                }
            }
        }
        //过滤出url,和用户的权限
        ActiveUser activeUser = new ActiveUser();
        activeUser.setRoles(roles);
        activeUser.setUser(userBean);
        activeUser.setMenus(menus);
        activeUser.setUrls(urls);
        activeUser.setPermissions(perms);
        return new SimpleAuthenticationInfo(activeUser, token, getName());
    }
}
