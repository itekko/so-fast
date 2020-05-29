package com.sofast;
import com.google.common.collect.Lists;
import java.time.LocalDateTime;

import com.sofast.system.entity.*;
import com.sofast.system.mapper.ResourceMapper;
import com.sofast.system.mapper.RoleResourceMapper;
import com.sofast.system.mapper.UserRoleMapper;
import com.sofast.system.service.IResourceService;
import com.sofast.system.service.IRoleService;
import com.sofast.system.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SoFastApplicationTests {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserService userService;

    @Autowired
	private UserRoleMapper userRoleMapper;

    @Autowired
	private RoleResourceMapper roleResourceMapper;

    @Autowired
	private IResourceService resourceService;

    @Test
	void testPassword(){
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String admin = bc.encode("admin");
		System.out.println(admin);
	}

	@Test
	void contextLoads() {
		/**
		 * 角色
		 */
		Role role = new Role();
		role.setName("admin");
		roleService.save(role);

		/**
		 * 用户
		 */
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setStatus(0L);
		user.setDescription("");
		userService.save(user);

		/**
		 * 中间数据-角色用户
		 */
		UserRole userRole = new UserRole();
		userRole.setRoleId(role.getId());
		userRole.setUserId(user.getId());
		userRoleMapper.insert(userRole);

		/**
		 * 资源
		 */
		Resource resource = new Resource();
		resource.setName("系统设置");
		resource.setUrl("/system");
		resource.setType(0L);
		resource.setPermission("/system");
		resource.setOrders(0);
		resource.setIcon("");
		resource.setParentId(0L);
		resource.setDescription("");

		resourceService.save(resource);

		/**
		 * 角色资源中间数据
		 */
		RoleResource roleResource = new RoleResource();
		roleResource.setResourceId(resource.getId());
		roleResource.setRoleId(role.getId());
		roleResourceMapper.insert(roleResource);

	}

}
