package com.rcronald.ms.jsmproject.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	@Profile("dev")
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(customerAPIInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(customerPaths())
				.build().
				securitySchemes(Arrays.asList(basicAuth()));
	}

	@Bean
	@Profile("prod")
	public Docket apiProd() {
		return new Docket(DocumentationType.SWAGGER_2).enable(false).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
	
	private Predicate<String> customerPaths() {
		return Predicates.or(
				regex("/api/v1/customer"),
				regex("/api/v1/customer/kpi"));
	}

	private ApiInfo customerAPIInfo() {
		return new ApiInfoBuilder().title("Customers API")
				.description("Customers platform API ")
				.license("Apache License Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0").build();
	}

	private SecurityScheme basicAuth() {
		return new BasicAuth("Basic Authentication");
	}
}
