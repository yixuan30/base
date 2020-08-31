package com.pactera.sys.entity.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class RoleQuery {
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String remark;

    @ApiModelProperty(value = "角色状态")
    private Boolean state;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    private Date updateTime;

}
