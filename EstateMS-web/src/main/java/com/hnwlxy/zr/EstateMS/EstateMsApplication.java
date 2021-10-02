package com.hnwlxy.zr.EstateMS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages = {"com.hnwlxy.zr.EstateMS"})
@MapperScan("com.hnwlxy.zr.EstateMS.biz.mapper")
@EnableAutoConfiguration
@EnableAsync
@EnableSwagger2
@ServletComponentScan("com.hnwlxy.zr.EstateMS.web.filter")
@SpringBootApplication
public class EstateMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstateMsApplication.class, args);
	}



}
