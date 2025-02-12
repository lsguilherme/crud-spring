package com.guilhermels.crud.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Documentação API CRUD-JAVA")
                        .version("1.0")
                        .description("Primeira versão da API CRUD-JAVA onde listamos apenas os produtos.")
                        .license(new License().url("teste-url").name("teste-name").identifier("teste-indetifier"))


                );

    }
}
