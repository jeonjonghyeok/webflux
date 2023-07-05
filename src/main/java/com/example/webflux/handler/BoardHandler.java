package com.example.webflux.handler;

import com.example.webflux.Response;
import com.example.webflux.model.Board;
import com.example.webflux.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BoardHandler {

    private final BoardService boardService;

    public Mono<ServerResponse> getBoards(ServerRequest request) {
        Flux<Board> boards = boardService.getBoards();
        return Response.success(boards);
    }

    public Mono<ServerResponse> getBoard(ServerRequest request) {
        String boardId = request.pathVariable("boardId");
        Mono<Board> board = boardService.getBoard(boardId);
        return Response.success(board);
    }

    public Mono<ServerResponse> createBoard(ServerRequest request) {
        Mono<Board> boardMono = request.bodyToMono(Board.class);
        Mono<Board> createdBoard = boardService.saveBoard(boardMono);
        return Response.success(createdBoard);
    }
}