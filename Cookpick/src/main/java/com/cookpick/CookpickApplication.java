package com.cookpick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@PropertySource(value = { "classpath:message.properties" })
@SpringBootApplication
public class CookpickApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CookpickApplication.class, args);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configurePathMatch(configurer);
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);	
	}
	
	

}
