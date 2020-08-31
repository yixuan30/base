package com.pactera.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FzMenu对象", description="")
public class FzMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单id")
    @TableId(value = "menu_id", type = IdType.ID_WORKER_STR)
    private String menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "父类菜单id")
    private String parentId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "0：不展开，1：展开")
    private Integer open;

    @ApiModelProperty(value = "0：不可用，1：可用")
    private Integer available;

    @ApiModelProperty(value = "类型 0：菜单，1：按钮")
    private Integer type;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "排序")
    private Long orderNum;


}
