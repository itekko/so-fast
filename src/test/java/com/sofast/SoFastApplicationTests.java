package com.sofast;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sofast.system.entity.*;
import com.sofast.system.mapper.*;
import com.sofast.system.service.IResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SoFastApplicationTests {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private IResourceService resourceService;

    @Test
    public void test02() throws Exception{
        //创建 cache ，过期时间 2 s
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build();
        //向缓存中添加 数据 K V 形式
                cache.put("hello","where are you");
        // 获取 key = hello 的 值
                System.out.println(cache.getIfPresent("hello"));
        // 延迟3 秒
                Thread.sleep(1000 * 3);
                cache.put("world","深圳");
        // return null if not present
                System.out.println(cache.getIfPresent("hello"));
                System.out.println(cache.getIfPresent("world"));
    }

    @Test
    public void test04(){
        List<Resource> tree = resourceService.getTree("1277094485221711873");
        System.out.println(tree);
    }

    @Test
    public void test01(){
        Resource system = new Resource();
        system.setName("系统设置");
        system.setUrl("/system");
        system.setType("1277079955561279489");
        system.setPermission("system");
        system.setOrders(0);
        system.setParentId("0");
        resourceMapper.insert(system);

        Resource userManage = new Resource();
        userManage.setName("用户管理");
        userManage.setUrl("/user");
        userManage.setType("1277079955561279489");
        userManage.setPermission("user");
        userManage.setOrders(0);
        userManage.setParentId(system.getId());
        resourceMapper.insert(userManage);

        Resource roleManage = new Resource();
        roleManage.setName("角色管理");
        roleManage.setUrl("/role");
        roleManage.setType("1277079955561279489");
        roleManage.setPermission("role");
        roleManage.setOrders(1);
        roleManage.setParentId(system.getId());
        resourceMapper.insert(roleManage);

        Resource log = new Resource();
        log.setName("日志记录");
        log.setUrl("/log");
        log.setType("1277079955561279489");
        log.setPermission("log");
        log.setOrders(1);
        log.setParentId("0");
        resourceMapper.insert(log);

        Resource operation = new Resource();
        operation.setName("操作日志");
        operation.setUrl("/operation");
        operation.setType("1277079955561279489");
        operation.setPermission("operation");
        operation.setOrders(0);
        operation.setParentId(log.getId());
        resourceMapper.insert(operation);

        Role role = new Role();
        role.setName("超级管理员");
        role.setCode("admin");
        roleMapper.insert(role);

        Stream.of(system.getId(), userManage.getId(), roleManage.getId(), log.getId(), operation.getId())
                .forEach(it -> {
                    RoleResource roleResource = new RoleResource();
                    roleResource.setResourceId(it);
                    roleResource.setRoleId(role.getId());
                    roleResourceMapper.insert(roleResource);
                });

        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setStatus(0);
        userMapper.insert(user);

        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRoleMapper.insert(userRole);
    }

    @Test
    public void test03(){
        /*Dictionary dictionary = new Dictionary();
        dictionary.setModule("system");
        dictionary.setCode("RESOURCE");
        dictionary.setType("0");
        dictionary.setName("资源类型");
        dictionary.setParentId(CommonContants.System.DICTIONARY_ROOT_ID);
        dictionaryMapper.insert(dictionary);

        Dictionary menu = new Dictionary();
        menu.setModule("system");
        menu.setCode("RESOURCE");
        menu.setType("1");
        menu.setName("菜单");
        menu.setParentId(dictionary.getId());
        dictionaryMapper.insert(menu);

        Dictionary button = new Dictionary();
        button.setModule("system");
        button.setCode("RESOURCE");
        button.setType("2");
        button.setName("按钮");
        button.setParentId(dictionary.getId());
        dictionaryMapper.insert(button);*/
    }

}
