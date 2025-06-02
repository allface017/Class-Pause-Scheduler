package com.example.classpausescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.classpausescheduler.mapper")
public class ClassPauseSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassPauseSchedulerApplication.class, args);
	}

}
