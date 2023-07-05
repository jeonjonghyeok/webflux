package com.example.webflux.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.net.URI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WebConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/swagger-ui.html"),
                request -> ServerResponse.temporaryRedirect(
                    URI.create("/webjars/swagger-ui/index.html?url=/v3/api-docs")).build())
            .andRoute(RequestPredicates.GET("/v3/api-docs"),
                request -> ServerResponse.ok().bodyValue(buildOpenAPI()));
    }

    private OpenAPI buildOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Custom API"))
            .components(new Components())
            .addServersItem(new Server().url("http://localhost:" + 8080 + "/"));
    }
}
