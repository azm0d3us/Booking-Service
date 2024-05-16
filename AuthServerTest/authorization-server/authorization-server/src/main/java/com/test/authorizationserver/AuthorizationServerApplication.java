package com.test.authorizationserver;

import com.test.authorizationserver.controllers.UtenteController;
import com.test.authorizationserver.entities.Utente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		var one = User.withDefaultPasswordEncoder().roles("admin").username("admin").password("pw").build();
		var two = User.withDefaultPasswordEncoder().roles("user").username("user").password("pw").build();
//		return new InMemoryUserDetailsManager(one, two);


		UtenteController uc = new UtenteController();
		uc.utenti.add(new Utente(1L, "admin", "pw"));
		uc.utenti.add(new Utente(2L, "user", "pw"));
		uc.utenti.add(new Utente(3L, "gianni", "gianni"));

		List<UserDetails> clienti = new ArrayList<>();

		for (Utente u: uc.utenti) {
			clienti.add(User.withDefaultPasswordEncoder().roles("user").username(u.getUsername()).password(u.getPassword())
							.build()
					);
		}
		return new InMemoryUserDetailsManager(clienti);
	}

}
