package com.amx.material;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.amx.material.mapper")
@SpringBootApplication
public class MaterialApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaterialApplication.class, args);
	}

}
