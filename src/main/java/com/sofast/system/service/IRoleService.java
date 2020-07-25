package com.sofast.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofast.system.entity.Role;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
public interface IRoleService extends IService<Role> {

    public List<Role> selectListByUserId(String userId);
}
