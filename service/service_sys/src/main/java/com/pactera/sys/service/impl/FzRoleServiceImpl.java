package com.pactera.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pactera.config.exceptionhandler.pacteraException;
import com.pactera.sys.converter.RoleConverter;
import com.pactera.sys.entity.FzMenu;
import com.pactera.sys.entity.FzRole;
import com.pactera.sys.entity.FzRoleMenu;
import com.pactera.sys.entity.enums.RoleStatusEnum;
import com.pactera.sys.entity.page.PageVo;
import com.pactera.sys.entity.role.RoleVo;
import com.pactera.sys.mapper.FzRoleMapper;
import com.pactera.sys.service.FzMenuService;
import com.pactera.sys.service.FzRoleMenuService;
import com.pactera.sys.service.FzRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-19
 */
@Service
public class FzRoleServiceImpl extends ServiceImpl<FzRoleMapper, FzRole> implements FzRoleService {

    @Autowired
    private FzRoleMenuService fzRoleMenuService;
    @Autowired
    private FzMenuService fzMenuService;
    @Autowired
    private  FzRoleService fzRoleService;
    /*
    * 角色授权
    * */
    @Override
    public void authority(String id, String[] mids) {
        FzRole role = baseMapper.selectById(id);
        if(role==null){
            throw new pacteraException(2001,"角色不存在");
        }
        QueryWrapper<FzRoleMenu> query = new QueryWrapper<>();
        query.like("role_id",id);
        fzRoleMenuService.remove(query);

        //删除原来的权限
        fzRoleMenuService.removeById(id);
        //添加现在的权限
        if(mids.length>0){
            for(String mid: mids){
                FzMenu menu = fzMenuService.getById(mid);
                if(menu==null){
                    throw new pacteraException(2001,"菜单权限不存在");
                }else {
                    FzRoleMenu fzRoleMenu = new FzRoleMenu();
                    fzRoleMenu.setRoleId(id);
                    fzRoleMenu.setMenuId(mid);
                    fzRoleMenuService.save(fzRoleMenu);
                }

            }
        }


    }
    /*
    * 根据角色id获取菜单
    * */
    @Override
    public List<String> findMenuIdsByRoleId(String id) {
        List<String> ids = new ArrayList<>();
        QueryWrapper<FzRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        List<FzRoleMenu> roleMenuList = fzRoleMenuService.list(queryWrapper);
        if(!CollectionUtils.isEmpty(roleMenuList)) {
            for (FzRoleMenu fzRoleMenu : roleMenuList) {
                ids.add(fzRoleMenu.getMenuId());
            }
        }
        return ids;
    }
    /*
    * 添加角色
    * */
    @Override
    public void add(FzRole role) {
        String roleName = role.getRoleName();
        QueryWrapper<FzRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",roleName);
        FzRole one = fzRoleService.getOne(queryWrapper);
        if(one!=null){
            throw new pacteraException(2001,"角色已经存在");
        }
        fzRoleService.save(role);
    }
    /*
    * 根据id删除角色
    * */
    @Override
    public void deleteById(String id) {
        FzRole role = fzRoleService.getById(id);
        if(role == null){
            throw new pacteraException(2001,"角色不存在");
        }
        fzRoleService.removeById(id);
        //删除对应的菜单
        QueryWrapper<FzRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        fzRoleMenuService.remove(queryWrapper);
    }
    /*
    * 更新角色
    * */
    @Override
    public void upd(String id, FzRole role) {
        FzRole fzRole = fzRoleService.getById(id);
        if(fzRole == null){
            throw new pacteraException(2001,"要跟新的角色不存在");
        }
        String name = role.getRoleName();
        QueryWrapper<FzRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",name);

        FzRole roleOne = fzRoleService.getOne(queryWrapper);
        if(roleOne != null){
            if(!id.equals(roleOne.getRoleId())){
              throw new pacteraException(2001,"角色名已经存在");
            }
        }
        role.setRoleId(id);
        fzRoleService.updateById(role);
    }
    /*
    * 展示角色列表
    * */
    @Override
    public PageVo<RoleVo> findRoleList(Integer pageNum, Integer pageSize, RoleVo roleVo) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<FzRole> queryWrapper = new QueryWrapper<>();
        String roleName = roleVo.getRoleName();
        if(roleName!=null&&!"".equals(roleName)){
            queryWrapper.eq("role_name",roleName);
        }
        List<FzRole> roleList = fzRoleService.list(queryWrapper);
        List<RoleVo> roleQueries = RoleConverter.converterToRoleQueryList(roleList);
        PageInfo<FzRole> info = new PageInfo<>(roleList);
        return new PageVo<>(info.getTotal(),roleQueries);
    }
    /*
    * 查找所有的角色
    * */
    @Override
    public List<FzRole> findAll() {
        return baseMapper.selectAll();
    }
    /*
    * 更新角色状态
    * */
    @Override
    public void updateStatus(String id, Boolean state) {
        FzRole role = baseMapper.selectById(id);
        if(role == null){
            throw new pacteraException(2001,"编辑的角色信息不存在");
        }
        FzRole t = new FzRole();
        t.setRoleId(id);
        t.setState(state ? RoleStatusEnum.DISABLE.getStatusCode():RoleStatusEnum.AVAILABLE.getStatusCode());
        baseMapper.update(t,null);
    }
    /*
    * 编辑角色信息
    * */
    @Override
    public RoleVo edit(String id) {
        FzRole role = baseMapper.selectById(id);
        if(role == null){
            throw new pacteraException(2001,"编辑的角色信息不存在");
        }
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role,roleVo);
        return roleVo;
    }

}
