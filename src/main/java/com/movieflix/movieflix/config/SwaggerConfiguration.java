package com.movieflix.movieflix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI getOpenAPI() {

        Contact contact = new Contact();
        contact.name("Ryan Torres");
        contact.email("Ryan@gmail.com");

        Info info = new Info();
        info.title("MovieFlix");
        info.version("v1");
        info.description("Uma api de gereciamento de filmes");
        info.contact(contact);
        return new OpenAPI().info(info);
    }
}
