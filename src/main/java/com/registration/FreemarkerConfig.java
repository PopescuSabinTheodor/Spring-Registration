package com.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * Configuration class that sets the path to the template
 *
 */
public class FreemarkerConfig {

	/**
	 * Sets the path from where to load the templates
	 * @return bean
	 */
	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("/templates/");
		return bean;
	}
}