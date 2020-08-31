package com.pactera.sys.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class UserVo {
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    private  String nickName;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "状态")
    private Boolean status;

    private  String email;

    private String password;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    private Date birth;



}
