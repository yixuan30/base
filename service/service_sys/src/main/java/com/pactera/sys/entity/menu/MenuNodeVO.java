package com.pactera.sys.entity.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class MenuNodeVO {
    @ApiModelProperty(value = "菜单id")
    @TableId(value = "menu_id", type = IdType.ID_WORKER_STR)
    private String menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单URL")
    private String url=null;

    @ApiModelProperty(value = "父类菜单id")
    private String parentId;

    @ApiModelProperty(value = "图标")
    private String icon;


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

    private boolean disabled;

    private List<MenuNodeVO> childern =new ArrayList<>();

    /*
     * 排序,根据order排序
     */
    public static Comparator<MenuNodeVO> order(){
        Comparator<MenuNodeVO> comparator = (o1, o2) -> {
            if(o1.getOrderNum() != o2.getOrderNum()){
                return (int) (o1.getOrderNum() - o2.getOrderNum());
            }
            return 0;
        };
        return comparator;
    }


}
