package com.absolutely.tictactoe.service;

import com.absolutely.tictactoe.entity.GamesEntity;

import java.util.List;

public interface IGameService {
    List<GamesEntity> getOpenGames();
}
