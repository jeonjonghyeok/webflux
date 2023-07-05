package com.example.webflux_api.repository;

import com.example.webflux.model.Board;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BoardRepository {

        private final R2dbcEntityTemplate template;

    public BoardRepository(R2dbcEntityTemplate template) {
        this.template = template;
    }

    public Mono<Board> findById(String id) {
        return template.selectOne(
                Query.query(Criteria.where("id").is(id)
            )
            , Board.class);
    }

    public Flux<Board> findAll() {
        return template.select(Query.query(Criteria.empty()), Board.class);
    }

    public Mono<Void> insert(Mono<Board> boardMono) {
        return boardMono.flatMap(template::insert)
            .then();
    }

}
