package com.nco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableMongoRepositories
public class AppConfig extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(AppConfig.class);
	    }
	 
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/*");
	            }
	        };
	    }
}
