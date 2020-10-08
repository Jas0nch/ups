package com.csc540.ups;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan("com.csc540.ups.dao")
//@ComponentScan("com.csc540.ups.*")
public class UpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpsApplication.class, args);
	}
}
