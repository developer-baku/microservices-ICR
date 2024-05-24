package org.services.reviewservice;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReviewServiceApplication {
	@Bean
	public OpenAPI api(){
		return new OpenAPI().info(new Info().title("Review service API").description("This is REST API for Review service").version("v1.0")
		).externalDocs(new ExternalDocumentation().description("You can refer to the documentation of Spring doc OpenApi").url("https://springdoc.org/#Introduction"));
	}
	public static void main(String[] args) {
		SpringApplication.run(ReviewServiceApplication.class, args);
	}

}
