package com.sofast.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofast.system.entity.Resource;

import java.util.List;

/**
 * <p>
 * 系统资源表 服务类
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 根据用户ID获取资源树
     * @param userId
     * @return
     */
    List<Resource> getTree(String userId);

    /**
     * 根据用户ID获取资源列表
     * @param userId
     * @return
     */
    List<Resource> selectListByUserId(String userId);
}
