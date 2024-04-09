package com.nnamdi.gpi.dronemanagementappwebflux.config;

import com.nnamdi.gpi.dronemanagementappwebflux.util.PropertySourceResolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


/**
 * Swagger configuration
 *
 * @author Nwabuokei Nnamdi
 */
@Configuration
public class SwaggerConfig {

    PropertySourceResolver propertySourceResolver;

    @Autowired
    public SwaggerConfig(PropertySourceResolver propertySourceResolver) {
        this.propertySourceResolver = propertySourceResolver;
    }

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.email("nwabuokeinnamdi19@gmail.com");
        contact.name("Nwabuokei Nnamdi");

        Info info = new Info()
                .title(propertySourceResolver.getProjectName())
                .version(propertySourceResolver.getProjectVersion())
                .contact(contact)
                .description(propertySourceResolver.getProjectDescription());
        return new OpenAPI().info(info);

    }


    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*"); // Allow requests from any origin
        corsConfig.addAllowedMethod("*"); // Allow all HTTP methods
        corsConfig.addAllowedHeader("*"); // Allow all headers
        corsConfig.setAllowCredentials(true); // Allow sending credentials like cookies
        corsConfig.setMaxAge(3600L); // Set max age of preflight request to 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
