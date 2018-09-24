package com.contoso;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/*import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@link UserApplication} is a cloud-native Spring Boot application that manages
 * a bounded context for @{link User}, @{link Account}, @{link CreditCard}, and @{link Address}
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableEurekaClient
@RestController
@EnableHystrix
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
    
	@RequestMapping("/user")
	public Principal user(Principal user,HttpServletRequest request) {
		// /user === http://192.168.99.100:8181/uaa/user or http://localhost:8181/uaa/user
		System.out.println("/user === "+request.getRequestURL().toString());
		return user;
	}
	        
/*    @Component
    public static class CustomizedRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config.setBasePath("/api");
        }
    }*/
}
