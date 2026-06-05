package com.eduardodev.banking_system_api.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${app.server-url:http://localhost:8081}")
    private String serverUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url(serverUrl)
                                .description("Servidor ativo")
                ))
                .info(new Info()
                        .title("Banking System API")
                        .version("1.0")
                        .description("API REST para gerenciamento de contas bancárias e movimentações financeiras.\n" +
                                "            \n" +
                                "            ## Funcionalidades\n" +
                                "            - Criação e gerenciamento de contas bancárias\n" +
                                "            - Depósito e saque por conta\n" +
                                "            - Transferência entre contas\n" +
                                "            - Histórico de transações\n" +
                                "            \n" +
                                "            #### Links\n" +
                                "            - [GitHub](https://github.com/Antonio-Eduardo/Banking_System_API)\n" +
                                "            - [LinkedIn](https://www.linkedin.com/in/antonio-eduardo-moreira-oliveira-418828242/)")
                        .contact(new Contact()
                                .name("Antonio Eduardo")
                                .email("eduardo.moreira.java@gmail.com")));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false);
            }
        };
    }
}