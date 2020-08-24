package com.pactera.sys.entity.menu;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class MenuVO {
    private String menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "父类菜单id")
    private String parentId;

    @ApiModelProperty(value = "图标")
    private String icon;


    @ApiModelProperty(value = "0：不展开，1：展开")
    private Integer open;


    @ApiModelProperty(value = "类型 0：菜单，1：按钮")
    private Integer type;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "排序")
    private Long orderNum;

    private boolean disabled;

}
