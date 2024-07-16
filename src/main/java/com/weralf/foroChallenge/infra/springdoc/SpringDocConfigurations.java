package com.weralf.foroChallenge.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API Forohub")
                        .description("API Rest de la aplicación Forohub, que contiene las funcionalidades de CRUD de topicos, respuestas, cursos y usuarios.")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("backend@forohub"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://forohub/api/licencia")));  }

    @Bean
    public String message(){
        System.out.println("Bearer is working");
        return null;
    }
}