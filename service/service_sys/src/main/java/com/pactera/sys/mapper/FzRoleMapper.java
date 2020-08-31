package com.pactera.sys.mapper;

import com.pactera.sys.entity.FzRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yixuan30
 * @since 2020-08-19
 */
public interface FzRoleMapper extends BaseMapper<FzRole> {

    List<FzRole> selectAll();

}
