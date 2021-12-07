package com.sme.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmeTodoApplicationTests {

	@Test
	void main() {
		String[] args = {};
		Assertions.assertEquals(0, args.length);
		SmeTodoApplication.main(args);
	}
}
