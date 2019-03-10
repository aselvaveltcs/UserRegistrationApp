package com.microservice.user.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

 

//@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "user-search-delete-service", configuration = UserSearchDeleteRibbonConfiguration.class)
public class UserRegistrationMicroSrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationMicroSrvApplication.class, args);
	}
}


@Configuration
class RestTemplateConfig {
	
	
 // Create a bean for restTemplate to call services

     @Bean
	 @LoadBalanced // Load balance between service instances running at different  ports. 
    public RestTemplate restTemplate() { return new RestTemplate(); }

	
	/*
	 * @Primary
	 * 
	 * @Bean RestTemplate restTemplate() { return new RestTemplate(); }
	 * 
	 * @LoadBalanced
	 * 
	 * @Bean RestTemplate loadBalanced() { return new RestTemplate(); }
	 */
	
}