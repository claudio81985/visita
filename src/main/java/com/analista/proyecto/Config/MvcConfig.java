package com.analista.proyecto.Config;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

		WebMvcConfigurer.super.addResourceHandlers(registry);

		String resourcePath = Paths.get("documentos").toAbsolutePath().toUri().toString();
		log.info(resourcePath);
		registry.addResourceHandler("/documentos/**").addResourceLocations("file:documentos/");

		// registry.addResourceHandler("/documentos/**").addResourceLocations(resourcePath);
	}

}
