package com.pactera.sys.converter;

import com.pactera.sys.entity.FzUser;
import com.pactera.sys.entity.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    /**
     * 转voList
     * @param users
     * @return
     */
    public  List<UserVo> converterToUserVOList(List<FzUser> users){
        List<UserVo> userVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(users)){
            for (FzUser user : users) {
                UserVo userVo = converterToUserVO(user);
                userVOS.add(userVo);
            }
        }
        return userVOS;
    }

    private  UserVo converterToUserVO(FzUser user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setStatus(user.getState()==0);
        return  userVo;
    }
}
