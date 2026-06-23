package com.example.ms_factorizacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsFactorizacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFactorizacionApplication.class, args);
	}

}
