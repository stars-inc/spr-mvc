package com.bessm.ownspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(args = "--app.author=stars-inc")
class OwnSpringApplicationTests {

	@Test
	void applicationArgsIncludedAuthor(@Autowired ApplicationArguments args) {
		assertThat(args.getOptionNames()).containsOnly("app.author");
		assertThat(args.getOptionValues("app.author")).containsOnly("stars-inc");
	}
}