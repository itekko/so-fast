package com.sofast.system.service;

import com.sofast.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName);
}
