package com.example.webflux_api.service;

import com.example.webflux.model.Board;
import com.example.webflux.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Mono<Board> getBoard(String id) {
        return boardRepository.findById(id);
    }

    public Flux<Board> getBoards() {
        return boardRepository.findAll();
    }

    public Mono<Board> createBoard(Mono<Board> boardMono) {
        boardRepository.insert(boardMono);
        return Mono.just(new Board("123"));
    }

    public Mono<Board> updateBoard(String id, Mono<Board> boardMono) {
        return Mono.just(new Board("123"));
    }

    public Mono<Void> deleteBoard(String id) {
        return Mono.empty();
    }

}
