package com.sofast.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofast.common.enums.EnumErrorCode;
import com.sofast.common.exception.SoFastException;
import com.sofast.system.entity.Resource;
import com.sofast.system.mapper.ResourceMapper;
import com.sofast.system.service.IResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统资源表 服务实现类
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    private final static Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);

    public static final String ROOT_RESOURCE_ID = "0";
    /**
     * 根据用户ID获取资源树
     * @param userId
     * @return
     */
    @Override
    public List<Resource> getTree(String userId) {
        List<Resource> resources = this.selectListByUserId(userId);
        if(CollectionUtils.isEmpty(resources)){
            return Collections.EMPTY_LIST;
        }

        // 根据parentId字段分组
        Map<String, List<Resource>> groups = resources.stream().collect(Collectors.groupingBy(Resource::getParentId));
        if(CollectionUtils.isEmpty(groups)){
            return Collections.EMPTY_LIST;
        }

        /**
         * 顶级菜单
         */
        List<Resource> rootResource = groups.get(ROOT_RESOURCE_ID);
        if(CollectionUtils.isEmpty(rootResource)){
            log.error("您所查询的菜单树没有根节点,userId为:{}",userId);
            throw new SoFastException(EnumErrorCode.menuNotRootNode);
        }

        resourceLoop(groups, rootResource);

        return rootResource;
    }

    /**
     * 循环调用资源
     * @param groups
     * @param rootResource
     */
    private void resourceLoop(Map<String, List<Resource>> groups, List<Resource> rootResource) {
        rootResource.stream().forEach(it ->{
            addSonNode(it,groups);
        });
    }

    /**
     * 添加子节点
     * @param parentNode
     * @param groups
     */
    private void addSonNode(Resource parentNode, Map<String, List<Resource>> groups) {
        String id = parentNode.getId();
        if (groups.containsKey(id)) {
            List<Resource> resources = groups.get(id);
            parentNode.setResources(resources);
            resourceLoop(groups,resources);
        }
    }

    @Override
    public List<Resource> selectListByUserId(String userId) {
        return baseMapper.selectListByUserId(userId);
    }
}
