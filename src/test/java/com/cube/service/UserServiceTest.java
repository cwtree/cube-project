package com.cube.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.manager.UserManager;
import com.cube.pojo.doo.User;
import com.cube.service.impl.UserServiceImpl;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-2-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

	@Mock
	private UserManager userManager;

	@InjectMocks
	private UserServiceImpl userService = new UserServiceImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetUserById() {
		User user = User.builder().username("unittest").comment("unittest").age(1).build();
		Mockito.when(userManager.getUserById(Mockito.anyLong())).thenReturn(user);
		assertEquals("unittest", userService.getUserById(9).getName());
		/**
		 * Mockito.when(userManager.getUserById(Mockito.anyLong())).thenThrow(new
		 * RuntimeException());
		 */
	}

	/**
	 * 使用powermockito mock静态方法
	 */

}
