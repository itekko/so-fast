package com.sofast;

import com.sofast.system.entity.Role;
import com.sofast.system.service.IRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SoFastApplicationTests {

	@Autowired
	private IRoleService roleService;


	@Test
	void contextLoads() {
		Role role = new Role();
		role.setName("Chole");
		roleService.save(role);
	}

}
