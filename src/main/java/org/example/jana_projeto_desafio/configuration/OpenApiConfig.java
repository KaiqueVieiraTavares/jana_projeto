package org.example.jana_projeto_desafio.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Minha API", version = "v1", description = "Documentação da API"))
public class OpenApiConfig {
}
