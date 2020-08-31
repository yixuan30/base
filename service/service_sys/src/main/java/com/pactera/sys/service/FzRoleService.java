package com.pactera.sys.service;

import com.pactera.sys.entity.FzRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pactera.sys.entity.page.PageVo;
import com.pactera.sys.entity.role.RoleQuery;
import com.pactera.sys.entity.role.RoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-19
 */
public interface FzRoleService extends IService<FzRole> {

    void authority(String id, String[] mids);

    List<String> findMenuIdsByRoleId(String id);

    void add(FzRole role);

    void deleteById(String id);

    void upd(String id, FzRole role);

    PageVo<RoleVo> findRoleList(Integer pageNum, Integer pageSize, RoleVo roleVo);

    List<FzRole> findAll();

    void updateStatus(String id, Boolean state);

    RoleVo edit(String id);
}
