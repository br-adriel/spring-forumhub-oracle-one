package br.com.alura.forum_hub.infra.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SpringDocConfigurations {
	@Bean
	public OpenAPI customOpenAPI() {
		var securityScheme = new SecurityScheme()
				.type(Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT");
		return new OpenAPI().components(
				new Components().addSecuritySchemes("bearer-key", securityScheme));
	}
}
