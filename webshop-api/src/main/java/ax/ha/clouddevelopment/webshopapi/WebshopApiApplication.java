package ax.ha.clouddevelopment.webshopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WebshopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopApiApplication.class, args);
        System.out.printf("Navigate to http://localhost:5000 to find the API documentation of your API");
    }

    /*
    @Configuration
    public class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("https://localhost:4200").allowedMethods("*");
        }
    }
    */

    @Configuration
    public class CorsConfiguration {

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {

                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*");
                }
            };
        }
    }

}
