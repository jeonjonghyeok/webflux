package com.example.webflux_api.router;

import com.example.webflux.handler.BoardHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class BoardRouter {

    private final BoardHandler boardHandler;

    @Bean
    public RouterFunction<ServerResponse> boardRoutes() {
        return RouterFunctions.route(
            RequestPredicates.GET("/boards/{boardId}"), boardHandler::getBoard)
            .andRoute(RequestPredicates.GET("boards"), boardHandler::getBoards)
            .andRoute(RequestPredicates.POST("/boards"), boardHandler::createBoard)
            .andRoute(RequestPredicates.PUT("/boards/{boardId}"), boardHandler::updateBoard)
            .andRoute(RequestPredicates.DELETE("/board/{boardId}"), boardHandler::deleteBoard);
    }

}
