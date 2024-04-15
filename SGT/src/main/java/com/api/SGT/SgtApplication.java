package com.api.SGT;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SGT", version = "1",
		description = "API destinada à manutenção do funcionamento do SGT - Sistema de Gestão de Tarefas"))
public class SgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgtApplication.class, args);
	}

}
