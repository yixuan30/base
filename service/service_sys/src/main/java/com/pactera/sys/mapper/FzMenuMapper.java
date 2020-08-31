package com.pactera.sys.mapper;

import com.pactera.sys.entity.FzMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-21
 */
@Component
public interface FzMenuMapper extends BaseMapper<FzMenu> {

    List<FzMenu> selectAll();
}
