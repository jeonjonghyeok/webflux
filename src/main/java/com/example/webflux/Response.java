package com.example.webflux;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public record Response<T>(HttpStatus status, String code, T data) {
    public static <T> Mono<ServerResponse> success(Flux<T> dataFlux) {
        Flux<Response> responseFlux = dataFlux.flatMap(Response::createResponse);
        return ServerResponse.ok().body(responseFlux, Response.class);
    }

    public static <T> Mono<ServerResponse> success(Mono<T> dataMono) {
        Mono<Response> responseMono = dataMono.flatMap(Response::createResponse);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    private static <T> Mono<Response> createResponse(T data) {
        return Mono.just(new Response(HttpStatus.OK, "success", data));
    }
}
