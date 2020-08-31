package com.pactera.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pactera.sys.entity.FzMenu;
import com.pactera.sys.entity.FzRole;
import com.pactera.sys.entity.FzUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pactera.sys.entity.menu.MenuNodeVO;
import com.pactera.sys.entity.page.PageVo;
import com.pactera.sys.entity.user.UserEditVo;
import com.pactera.sys.entity.user.UserInfoVo;
import com.pactera.sys.entity.user.UserQuery;
import com.pactera.sys.entity.vo.UserInfo;
import com.pactera.sys.entity.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-19
 */
public interface FzUserService extends IService<FzUser> {

    void addUser(UserVo user);

    UserInfo getUserById(String id);

    void delUserByid(String id);

    void assignRoles(String id, String[] ids);

    String login(UserQuery userQuery);

    FzUser findUserByName(String username);

    List<FzRole> findRolesById(String userId);

    List<FzMenu> findMenuByRoles(List<FzRole> roles);

    UserInfoVo info();

    List<MenuNodeVO> findMenu();

    List<String> roles(String id);


    void updateStatus(String id, Boolean status);

    UserEditVo edit(String id);

    void update1(String id, UserEditVo userEditVo);

    PageVo<UserVo> findUserList(Integer pageNum, Integer pageSize, UserVo userVo);
}
