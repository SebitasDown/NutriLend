package com.nutriLens.NutriLens.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NutriLens API")
                        .description("API REST para la aplicación NutriLens - Análisis nutricional de comidas mediante IA")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("NutriLens Team")
                                .email("soporte@nutrilens.com"))
                        .license(new License()
                                .name("Licencia Privada")
                                .url("https://nutrilens.com/license")))
                .servers(List.of(
                        new Server().url("/").description("Servidor actual"),
                        new Server().url("http://localhost:8080").description("Local"),
                        new Server().url("https://api.nutrilens.com").description("Producción")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Token JWT. Obtener en POST /auth/login o /auth/register e ingresar como: Bearer &lt;token&gt;")));
    }
}
