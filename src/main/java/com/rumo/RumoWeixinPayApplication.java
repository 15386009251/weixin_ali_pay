package com.rumo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rumo.mapper")
public class RumoWeixinPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RumoWeixinPayApplication.class, args);
	}
}
