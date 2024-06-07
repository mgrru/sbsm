package com.mgrru.sbsm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mgrru.sbsm.service.MasterService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SbsmApplicationTests {

	@Autowired
	private MasterService masterService;

	@Test
	void contextLoads() {
		String result = masterService.login(1, "123");
		if (result.equals("用户不存在或密码不正确!")) {
			log.error(result);
		} else {
			log.info("token:" + result);
		}

	}

}
