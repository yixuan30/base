package com.pactera.sys.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "状态")
    private String state;


}
