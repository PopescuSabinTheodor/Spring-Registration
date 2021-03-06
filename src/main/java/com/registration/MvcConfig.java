package com.registration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for views
 *
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	/**
	 * Sets views
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/").setViewName("register");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/change-password").setViewName("changePassword");
	}

}
