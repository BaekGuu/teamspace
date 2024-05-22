package com.ssafy.ws.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//Swagger-UI 확인
//http://localhost/swagger-ui.html

//@OpenAPIDefinition(
//	    info = @Info(
//	        title = "SSAFY Board API 명세서",
//	        description = "<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 Board API<br><img src=\"/assets/img/ssafy_logo.png\" width=\"150\">",
//	        version = "v1",
//	        contact = @Contact(
//	            name = "hissam",
//	            email = "hissam@ssafy.com",
//	            url = "http://edu.ssafy.com"
//	        )
//	    )
//	)

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().title("Baek9 API 명세서").description(
				"<h3>Baek9 API Reference for Developers</h3>Swagger를 이용한  API<br>")
				.version("v1").contact(new io.swagger.v3.oas.models.info.Contact().name("jaseung")
						.email("jaeseung@naver.com"));

		return new OpenAPI().components(new Components()).info(info);
	}

	@Bean
	public GroupedOpenApi boardApi() {
		return GroupedOpenApi.builder().group("baek9-board").pathsToMatch("/board/**").build();
	}

	@Bean
	public GroupedOpenApi memberApi() {
		return GroupedOpenApi.builder().group("baek9-member").pathsToMatch("/member/**").build();
	}
	
	@Bean
	public GroupedOpenApi commentApi() {
		return GroupedOpenApi.builder().group("baek9-comment").pathsToMatch("/comment/**").build();
	}
	
	@Bean
	public GroupedOpenApi placeApi() {
		return GroupedOpenApi.builder().group("baek9-place").pathsToMatch("/place/**").build();
	}
	@Bean
	public GroupedOpenApi planApi() {
		return GroupedOpenApi.builder().group("baek9-plan").pathsToMatch("/plan/**").build();
	}
	/*
	@Bean
	public GroupedOpenApi fileApi() {
		return GroupedOpenApi.builder().group("ssafy-file").pathsToMatch("/file/**").build();
	}
	*/

}