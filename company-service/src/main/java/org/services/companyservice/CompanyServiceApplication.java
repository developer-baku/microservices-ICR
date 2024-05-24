package org.services.companyservice;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@EnableFeignClients
@SpringBootApplication
public class CompanyServiceApplication {

	@FeignClient(name = "REVIEW-SERVICE",url = "${review-service.url}")
	public interface CompanyClient{
		@GetMapping("/reviews/averageRating")
		Double getAverageRating(@RequestParam("companyId") Long id);
	} @Bean
	public OpenAPI api(){
		return new OpenAPI().info(new Info().title("Company service API").description("This is REST API for Company service").version("v1.0")
		).externalDocs(new ExternalDocumentation().description("You can refer to the documentation of Spring doc OpenApi").url("https://springdoc.org/#Introduction"));
	}

	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);
	}

}
