package com.example.webflux.service;

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

    public Mono<Board> saveBoard(Mono<Board> boardMono) {
        return boardMono.flatMap(boardRepository::save);
    }

}
