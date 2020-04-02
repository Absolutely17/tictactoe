package com.absolutely.tictactoe.service;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.repository.GamesRepository;
import com.absolutely.tictactoe.request.ConnectRequest;
import com.absolutely.tictactoe.request.MoveRequest;
import com.absolutely.tictactoe.response.ConnectResponse;
import com.absolutely.tictactoe.response.GameSimpleResponse;
import com.absolutely.tictactoe.response.MoveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService implements IGameService {

    @Autowired
    private GamesRepository gamesRepository;

    @Override
    public List<GameSimpleResponse> getOpenGames()
    {
        List<GameSimpleResponse> openGames = new ArrayList<>();
        List<GamesEntity> foundGames = gamesRepository.findOpenGames(true);
        for(GamesEntity g : foundGames)
            openGames.add(new GameSimpleResponse(g));
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
    public GameSimpleResponse addGame(String name)
    {
        GamesEntity game = new GamesEntity(name);
        gamesRepository.save(game);
        return new GameSimpleResponse(game);
    }

    @Override
    public MoveResponse doMove(GamesEntity game, MoveRequest moveRequest)
    {
        int cell = moveRequest.getCell().intValue();
        byte[] cells = game.getCells();
        if (game.getCurrentMove().equals(moveRequest.getName())) {
            if (cells[cell] == 0) {
                if (game.getCurrentMove().equals(game.getFirstPlayer())) {
                    cells[cell] = 1;
                    game.setCurrentMove(game.getSecondPlayer());
                }
                else {
                    cells[cell] = 2;
                    game.setCurrentMove(game.getFirstPlayer());
                }
                game.setLastMove(cell);
                if (checkOnWin(cells, cell)) {
                    game.setWinner(moveRequest.getName());
                    game.setCurrentMove(null);
                }
                gamesRepository.save(game);
                return new MoveResponse(true, moveRequest.getCell());
            }
        }
        return new MoveResponse(false, moveRequest.getCell());
    }
    public boolean checkOnWin(byte[] cells, int cell)
    {
        int i,j;
        int row = cell/19;
        int maxCellInRow = row * 19 + 19;
        int countRow=1;
        for (i=cell+1;i<maxCellInRow;i++)
            if (cells[i]==1)
                countRow++;
            else break;
        for (j=cell-1;j>=row*19;j--)
            if (cells[j]==1)
                countRow++;
            else break;
        if (countRow==5)
         return true;
        int countCell = 1;
        for (i=cell+19;i<361;i+=19)
            if (cells[i]==1)
                countCell++;
            else break;
        for (j=cell-19;j>0;j-=19)
            if (cells[j]==1)
                countCell++;
            else break;
        if (countCell==5)
            return true;
        int countDiag = 1;
        for (i=1,j=cell+20;j<maxCellInRow+19*i && j<361;i++, j+=20)
            if (cells[j]==1)
                countDiag++;
            else break;
        for (i=1,j=cell-20;j>=row*19-19*i && j>=0;i++,j-=20)
            if (cells[j]==1)
                countDiag++;
            else break;
        if (countDiag==5)
            return true;
        countDiag = 1;
        for (i=1,j=cell-18;j<maxCellInRow-19*i && j>0;i++,j-=18)
            if (cells[j]==1)
                countDiag++;
            else break;
        for (i=1,j=cell+18;j>=row*19+19*i && j<=361;i++, j+=18)
            if (cells[j]==1)
                countDiag++;
            else break;
        if (countDiag==5)
            return true;
        return false;
    }
    @Override
    public GameSimpleResponse exitGame(Long id, String name)
    {
        GamesEntity game = getGamesById(id);
        game.setIsClosedByPlayer(true);
        gamesRepository.save(game);
        return new GameSimpleResponse(game);
    }
}
