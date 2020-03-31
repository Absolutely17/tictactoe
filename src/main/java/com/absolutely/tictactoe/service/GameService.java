package com.absolutely.tictactoe.service;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.repository.GamesRepository;
import com.absolutely.tictactoe.request.ConnectRequest;
import com.absolutely.tictactoe.response.ConnectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public GamesEntity getGamesById(Long id)
    {
        Optional<GamesEntity> gamesOptional = gamesRepository.findById(id);
        return gamesOptional.get();
    }

    @Override
    public ConnectResponse edit(GamesEntity game)
    {
        gamesRepository.save(game);
        return new ConnectResponse(game);
    }
}
