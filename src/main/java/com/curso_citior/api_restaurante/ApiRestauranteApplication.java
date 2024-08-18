package com.curso_citior.api_restaurante;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiRestauranteApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiRestauranteApplication.class, args);
  }

  @Bean
  public OpenAPI informacionPersonalizada() {
    return new OpenAPI().info(
        new Info().title("API Restaurante").version("1.0.0").description("Desarrollo de REST API")
            .termsOfService("https://api-restaurante.org")
            .license(new License().name("Licencia 1.2.3").url("https://licencia.com")));
  }

  @Configuration
  public static class Myconfiguration{
    @Bean
    public WebMvcConfigurer corsConfigurer(){
      return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/**")
              .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
        }
      };
    }
  }
}
