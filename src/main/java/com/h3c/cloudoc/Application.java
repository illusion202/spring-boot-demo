package com.h3c.cloudoc;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication boostrap = new SpringApplication(Application.class);
		boostrap.setBanner(new ResourceBanner(new ClassPathResource("banner.txt")));
		boostrap.run(args);
	}
}
