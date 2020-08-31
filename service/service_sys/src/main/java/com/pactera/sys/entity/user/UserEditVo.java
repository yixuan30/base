package com.pactera.sys.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class UserEditVo {

    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    private Date birth;


}
