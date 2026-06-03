package com.eduardodev.banking_system_api.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
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
}
