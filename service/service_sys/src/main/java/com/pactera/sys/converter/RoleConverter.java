package com.pactera.sys.converter;

import com.pactera.sys.entity.FzRole;
import com.pactera.sys.entity.role.RoleQuery;
import com.pactera.sys.entity.role.RoleTransferItemVo;
import com.pactera.sys.entity.role.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {

    public static List<RoleVo> converterToRoleQueryList(List<FzRole> roleList) {
        List<RoleVo> roleQueries = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleList)){
            for(FzRole role : roleList){
                RoleVo roleVo = new RoleVo();
                BeanUtils.copyProperties(role,roleVo);
                roleVo.setState(role.getState()==0);
                roleQueries.add(roleVo);
            }
        }
        return roleQueries;
    }

    public static List<RoleTransferItemVo> converterToRoleTransferItem(List<FzRole> list) {
        List<RoleTransferItemVo> itemVoList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(FzRole role: list){
                RoleTransferItemVo itemVo = new RoleTransferItemVo();
                itemVo.setKey(role.getRoleId());
                itemVo.setLabel(role.getRoleName());
                itemVo.setDisabled(role.getState().equals("0"));
                itemVoList.add(itemVo);
            }
        }
        return itemVoList;
    }
}
