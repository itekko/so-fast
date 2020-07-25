package com.sofast.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofast.system.entity.User;
import com.sofast.system.mapper.UserMapper;
import com.sofast.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public User getUserByUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        User userOne = baseMapper.selectOne(userQueryWrapper);
        return userOne;
    }
}
