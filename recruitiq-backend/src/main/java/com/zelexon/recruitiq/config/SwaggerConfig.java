package com.zelexon.recruitiq.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI recruitIQOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RecruitIQ API")
                        .description("RecruitIQ Recruitment Platform APIs")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Zelexon")
                                .email("support@zelexon.com"))
                        .license(new License()
                                .name("Proprietary")));
    }
}

