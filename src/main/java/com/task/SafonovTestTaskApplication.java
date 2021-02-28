package com.task;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.task.utils.*;

@SpringBootApplication(scanBasePackages={"com.task.repositories","com.task.model", "com.task.service", "com.task.web","com.task"})
@EnableVaadin({"com.task"})
public class SafonovTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafonovTestTaskApplication.class, args);
	}
}
