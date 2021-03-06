package com.sofast.system.service.impl;

import com.sofast.system.entity.Role;
import com.sofast.system.mapper.RoleMapper;
import com.sofast.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 根据用户ID获取角色列表
     * @param userId
     * @return
     */
    @Override
    public List<Role> selectListByUserId(String userId) {
        return baseMapper.selectListByUserId(userId);
    }
}
