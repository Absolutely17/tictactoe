package com.absolutely.tictactoe.controller;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.request.ConnectRequest;
import com.absolutely.tictactoe.response.ConnectResponse;
import com.absolutely.tictactoe.response.GameSimpleResponse;
import com.absolutely.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {
    @Autowired
    private GameService gameService;

    @RequestMapping(
            method= RequestMethod.GET,
            path="/games",
            produces = "application/json"
    )
    public List<GameSimpleResponse> get()
    {
        return gameService.getOpenGames();
    }

    @RequestMapping(
            method=RequestMethod.POST,
            path="/game/{gameId}/connect",
            consumes = "application/json",
            produces = "application/json"
    )
    public ConnectResponse connectToGame(@RequestBody ConnectRequest connectRequest, @PathVariable(name="gameId") Long id){
            GamesEntity gamesToConnect = gameService.getGamesById(id);
            GamesEntity gameEdited = gamesToConnect.edit(connectRequest);
            return gameService.edit(gameEdited);
    }

    @RequestMapping(
            method=RequestMethod.POST,
            path="/game/create",
            consumes="application/json",
            produces="application/json"
    )
    public GameSimpleResponse createGame(@RequestBody Map<String, String> request){
        return gameService.addGame(request.get("name"));
    }
}
