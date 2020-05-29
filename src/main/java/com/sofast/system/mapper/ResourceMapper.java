package com.sofast.system.mapper;

import com.sofast.system.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统资源表 Mapper 接口
 * </p>
 *
 * @author ekko
 * @since 2020-05-29
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> selectByUserId(@Param("userId") Long userId);
}
