package com.sofast.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofast.system.entity.Resource;
import com.sofast.system.entity.User;
import com.sofast.system.mapper.ResourceMapper;
import com.sofast.system.mapper.UserMapper;
import com.sofast.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author ekko
 * @since 2020-05-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService,UserDetailsService {

    @Autowired
    private ResourceMapper resourceMapper;
    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wraper = new QueryWrapper<>();
        wraper.eq("username",username);
        User user = baseMapper.selectOne(wraper);
        if(user != null){
            Long id = user.getId();
            List<Resource> resources = resourceMapper.selectByUserId(id);
            user.setResources(resources);
        }

        return user;
    }
}
