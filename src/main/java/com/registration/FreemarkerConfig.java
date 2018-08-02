package com.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;


public class FreemarkerConfig {

	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("/templates/");
		return bean;
	}
}