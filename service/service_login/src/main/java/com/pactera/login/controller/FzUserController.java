package com.pactera.login.controller;


import com.pactera.commonUtils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/login/fz-user")
public class FzUserController {
    @GetMapping("/test")
    public R test(){
        return R.ok().data("test","8d2");
    }

}

