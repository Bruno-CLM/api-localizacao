package br.com.localizacaoapi.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public OpenAPI springShopOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.setComponents(new Components());
        openAPI.setPaths(new Paths());
        
        final Contact contact = new Contact()
        .name("Bruno Cunha")
        .email("madeirab54@gmail.com")
        .url("https://github.com/Bruno-CLM");

        final Info info = new Info();
        info.setDescription("API REST de cadastro de cidades.");
        info.setVersion("v0.0.1");
        info.setTitle("LOCALIZACAO API REST");
        info.setContact(contact);
        info.setLicense(new License().name("MIT License"));
        openAPI.setInfo(info);
        

        final Server server = new Server();
        server.setUrl("https://api-localizacao.herokuapp.com/");
        openAPI.setServers(Collections.singletonList(server));
        return openAPI;
    }

}
