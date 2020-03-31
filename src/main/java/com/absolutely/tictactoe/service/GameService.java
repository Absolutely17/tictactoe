package com.absolutely.tictactoe.service;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GameService implements IGameService {

    @Autowired
    private GamesRepository gamesRepository;

    @Override
    public List<GamesEntity> getOpenGames()
    {
        List<GamesEntity> openGames = gamesRepository.findOpenGames(true);
        return openGames;
    }
}
