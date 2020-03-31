package com.absolutely.tictactoe.service;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.request.ConnectRequest;
import com.absolutely.tictactoe.response.ConnectResponse;

import java.util.List;

public interface IGameService {
    List<GamesEntity> getOpenGames();
    GamesEntity getGamesById(Long id);
    ConnectResponse edit(GamesEntity game);
    GamesEntity addGame(String name);
}
