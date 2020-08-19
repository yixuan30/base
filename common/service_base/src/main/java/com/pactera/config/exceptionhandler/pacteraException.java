package com.pactera.config.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class pacteraException extends RuntimeException {

    //状态码
    @ApiModelProperty(value = "状态码")
    private  Integer Code;
    //出错信息
    private  String msg;
}
