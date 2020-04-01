package com.absolutely.tictactoe.service;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.request.MoveRequest;
import com.absolutely.tictactoe.response.ConnectResponse;
import com.absolutely.tictactoe.response.MoveResponse;

import java.util.List;

public interface IGameService {
    List<GamesEntity> getOpenGames();
    GamesEntity getGamesById(Long id);
    ConnectResponse edit(GamesEntity game);
    GamesEntity addGame(String name);
    MoveResponse doMove(GamesEntity game, MoveRequest moveRequest);
}
