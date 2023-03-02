package com.example.khedmabackend;

import com.example.khedmabackend.model.Addresse;
import com.example.khedmabackend.model.Employe;
import com.example.khedmabackend.model.Genre;
import com.example.khedmabackend.repo.UtilisateurRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KhedmaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhedmaBackendApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(UtilisateurRepo repo){
		return args -> {
			repo.insert(new Employe("seyl","123456789","email@gmail.com",
					"lyes","zenati", Genre.HOMME,"",new Addresse("bejaia","amizour")));
		};
	}
}
