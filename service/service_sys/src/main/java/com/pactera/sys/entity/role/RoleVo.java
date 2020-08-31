package com.pactera.sys.entity.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoleVo {
    private String roleId;

    private String roleName;

    private Boolean state;

    private String remark;

}
