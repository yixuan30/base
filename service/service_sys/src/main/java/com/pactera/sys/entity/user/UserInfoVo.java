package com.pactera.sys.entity.user;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserInfoVo {

    private String userName;

    private  String nickName;

    private String avatar;

    private Set<String> url;

    private Set<String> perms;

    private List<String> roles;

    private Boolean isAdmin=false;

}
