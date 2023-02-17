package com.example.usuariopowerUp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UsuarioPowerUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioPowerUpApplication.class, args);
	}

}
