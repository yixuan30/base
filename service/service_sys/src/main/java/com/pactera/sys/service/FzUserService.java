package com.pactera.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pactera.sys.entity.FzUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pactera.sys.entity.vo.UserInfo;
import com.pactera.sys.entity.vo.UserVo;

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

    String addUser(UserInfo user);


    int updateUser(UserInfo user);

    UserInfo getUserById(String id);

    void delUserByid(String id);

    Map<String, Object> pageListWeb(Page<FzUser> pageParam, UserVo userVo);

    void assignRoles(String id, Long[] ids);
}
