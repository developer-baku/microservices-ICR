package org.services.jobservice;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.services.jobservice.dto.ResponseCompany;
import org.services.jobservice.dto.ReviewResponse;
import org.services.jobservice.service.ServiceJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.util.List;

@EnableFeignClients
@SpringBootApplication
public class JobServiceApplication {
	@LoadBalanced
	@Bean
	public RestClient.Builder restClientBuilder() {
		return  RestClient.builder();
	}
	@FeignClient(name = "COMPANY-SERVICE"
			,url = "${company-service.url}")
	public interface CompanyI{
		@GetMapping(path = "/companies/{id}", consumes = "application/json")
		ResponseCompany getName(@PathVariable("id") Long id);
	}
	@FeignClient(name = "REVIEW-SERVICE",url = "${review-service.url}")
	public interface ReviewI{
		@GetMapping("/reviews")
		List<ReviewResponse> getR(@RequestParam("companyId") Long id);
	}
	@Bean
	public OpenAPI api(){
		return new OpenAPI().info(new Info().title("Job service API").description("This is REST API for Job service").version("v1.0")
		).externalDocs(new ExternalDocumentation().description("You can refer to the documentation of Spring doc OpenApi").url("https://springdoc.org/#Introduction"));
	}
	@Bean
	public ServiceJob serviceJob(){
		return new ServiceJob();
	}
	public static void main(String[] args) {
		SpringApplication.run(JobServiceApplication.class, args);
	}

}
