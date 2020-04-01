package com.absolutely.tictactoe.controller;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.request.MoveRequest;
import com.absolutely.tictactoe.response.MoveResponse;
import com.absolutely.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(
            method = RequestMethod.GET,
            path="/game/{id}/state",
            produces="application/json"
    )
    public GamesEntity getState(@PathVariable(name="id") Long id)
    {
        return gameService.getGamesById(id);
    }

    @RequestMapping(
            method=RequestMethod.POST,
            path="/game/{id}/move",
            consumes="application/json",
            produces="application/json"
    )
    public MoveResponse moveTo(@PathVariable(name="id") Long id, @RequestBody MoveRequest moveRequest)
    {
        GamesEntity game = gameService.getGamesById(id);
        return gameService.doMove(game, moveRequest);
    }
}
