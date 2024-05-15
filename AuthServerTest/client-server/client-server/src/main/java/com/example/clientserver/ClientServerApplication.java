package com.example.clientserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class ClientServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServerApplication.class, args);
	}

	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		var one = User.withDefaultPasswordEncoder().roles("admin").username("pippo").password("pippo").build();
		var two = User.withDefaultPasswordEncoder().roles("user").username("pluto").password("pluto").build() ;
		return new InMemoryUserDetailsManager(one, two);
	}
}
