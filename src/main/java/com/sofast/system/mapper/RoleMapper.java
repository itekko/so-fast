package com.sofast.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofast.system.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
public interface RoleMapper extends BaseMapper<Role> {

    public List<Role> selectListByUserId(@Param("userId") String userId);
}
