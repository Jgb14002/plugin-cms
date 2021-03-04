package com.github.jgb14002.contentmanagement;

import com.github.jgb14002.contentmanagement.security.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class ContentManagementApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ContentManagementApplication.class, args);
	}
}
