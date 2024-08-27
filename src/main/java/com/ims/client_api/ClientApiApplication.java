package com.ims.client_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
@OpenAPIDefinition(info = @Info(title = "Client-API", version = "1", description = "API desenvolvida para realizar um CRUD de clientes"))
public class ClientApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(ClientApiApplication.class, args);
	}

}
