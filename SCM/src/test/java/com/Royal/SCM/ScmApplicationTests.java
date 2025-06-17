package com.Royal.SCM;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.Royal.SCM.servises.EmailService;

@SpringBootTest
class ScmApplicationTests {

	private EmailService service;

	@Test
	void contextLoads() {
	}
	   @Test
		void sendEmailTest() {
		service.sendEmail("batchlcwd@gmail.com", "Just managing the emails",
				"this is scm project working on email service");
	}
//
}
