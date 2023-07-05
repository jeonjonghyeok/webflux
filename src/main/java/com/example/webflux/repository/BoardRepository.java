package com.example.webflux.repository;

import com.example.webflux.model.Board;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends ReactiveCrudRepository<Board, String> {


}
