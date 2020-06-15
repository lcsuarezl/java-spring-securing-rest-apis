package io.jzheaux.springsecurity.resolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication //(exclude = SecurityAutoConfiguration.class)
public class ResolutionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolutionsApplication.class, args);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				org.springframework.security.core.userdetails.User
						.withUsername("user")
						//.password("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W")
						.password("{bcrypt}$2a$10$tTbxE.NFIO7nezwMsmTGCO0TEJzxL8Nfed9ni9gp41BqO3FwiAI7i")//hello
						.authorities("resolution:read")
						.build());
	}

}
