package com.absolutely.tictactoe.service;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.repository.GamesRepository;
import com.absolutely.tictactoe.request.ConnectRequest;
import com.absolutely.tictactoe.request.MoveRequest;
import com.absolutely.tictactoe.response.ConnectResponse;
import com.absolutely.tictactoe.response.MoveResponse;
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

    @Override
    public GamesEntity addGame(String name)
    {
        GamesEntity game = new GamesEntity(name);
        gamesRepository.save(game);
        return game;
    }

    @Override
    public MoveResponse doMove(GamesEntity game, MoveRequest moveRequest)
    {
        int cell = moveRequest.getCell().intValue();
        if (game.getCurrentMove().equals(moveRequest.getName())) {
            if (game.getCells()[cell] == 0) {
                if (game.getCurrentMove().equals(game.getFirstPlayer())) {
                    game.getCells()[cell] = 1;
                    game.setCurrentMove(game.getSecondPlayer());
                }
                else {
                    game.getCells()[cell] = 2;
                    game.setCurrentMove(game.getFirstPlayer());
                }
                game.setLastMove(cell);
                gamesRepository.save(game);
                return new MoveResponse(true, moveRequest.getCell());
            }
        }
        return new MoveResponse(false, moveRequest.getCell());
    }
}
