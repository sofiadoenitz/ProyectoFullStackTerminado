package com.example.ms_juego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsJuegoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsJuegoApplication.class, args);
	}

}
