package com.gabrielferreira02.megasena.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API da megasena",
                description = "Api que simula a megasena, gerando resultados e registrando jogos para concorrer ao prêmio",
                version = "1.0.0",
                contact = @Contact(
                        name = "Gabriel Ferreira",
                        url = "https://github.com/gabrielferreira02",
                        email = "gabrielf.04.2002@gmail.com"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {
}
