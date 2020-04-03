package com.absolutely.tictactoe.repository;

import com.absolutely.tictactoe.entity.GamesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GamesRepository extends JpaRepository<GamesEntity, Long> {
    @Query("from GamesEntity r where r.opened =:isOpened")
    List<GamesEntity> findOpenGames(@Param("isOpened") boolean isOpened);
    @Query("from GamesEntity r where r.closedByPlayer <>true and r.winner =\"null\"")
    List<GamesEntity> findAllGames();
}
