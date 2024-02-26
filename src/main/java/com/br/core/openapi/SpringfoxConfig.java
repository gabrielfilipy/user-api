package com.br.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;

import io.github.classgraph.Resource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.SpringfoxWebConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/v1/**"))
				.build()
				.useDefaultResponseMessages(false)
				.ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				
				.apiInfo(apiInfoV1());
	}
	
	private ApiInfo apiInfoV1() {
		return new ApiInfoBuilder()
				.title("USER-SERVICE-API")
				.description("Microservice de Usuário.")
				.version("1")
				.contact(new Contact("Marcos Rocha", "https://www.senac.gov.br/", "mrocha@bnb.gov.br"))
				.build();
	}
	
}
