package com.example.webflux_api.handler;

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
        return ServerResponse.ok().body(boards, Board.class);
    }

    public Mono<ServerResponse> getBoard(ServerRequest request) {
        String boardId = request.pathVariable("boardId");
        Mono<Board> board = boardService.getBoard(boardId);
        return ServerResponse.ok().body(board, Board.class);
    }

    public Mono<ServerResponse> createBoard(ServerRequest request) {
        Mono<Board> boardMono = request.bodyToMono(Board.class);
        Mono<Board> createdBoard = boardService.createBoard(boardMono);
        return ServerResponse.ok().body(createdBoard, Board.class);
    }

    public Mono<ServerResponse> updateBoard(ServerRequest request) {
        String boardId = request.pathVariable("boardId");
        Mono<Board> boardMono = request.bodyToMono(Board.class);
        Mono<Board> updatedBoard = boardService.updateBoard(boardId, boardMono);
        return ServerResponse.ok().body(updatedBoard, Board.class);
    }

    public Mono<ServerResponse> deleteBoard(ServerRequest request) {
        String boardId = request.pathVariable("boardId");
        Mono<Void> voidMono = boardService.deleteBoard(boardId);
        return ServerResponse.ok().build(voidMono);
    }
}