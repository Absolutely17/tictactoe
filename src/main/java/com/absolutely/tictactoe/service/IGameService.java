package com.absolutely.tictactoe.service;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.request.MoveRequest;
import com.absolutely.tictactoe.response.ConnectResponse;
import com.absolutely.tictactoe.response.GameSimpleResponse;
import com.absolutely.tictactoe.response.MoveResponse;

import java.util.List;

public interface IGameService {
    List<GameSimpleResponse> getOpenGames();
    List<GameSimpleResponse> getAllGames();
    GamesEntity getGamesById(Long id);
    ConnectResponse connect(GamesEntity game);
    GameSimpleResponse addGame(String name);
    MoveResponse doMove(GamesEntity game, MoveRequest moveRequest);
    GameSimpleResponse exitGame(Long id, String name);
}
