package com.sofast.system.service.impl;

import com.sofast.system.entity.Resource;
import com.sofast.system.mapper.ResourceMapper;
import com.sofast.system.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统资源表 服务实现类
 * </p>
 *
 * @author ekko
 * @since 2020-05-29
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
