package com.absolutely.tictactoe.controller;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.request.ConnectRequest;
import com.absolutely.tictactoe.response.GameSimpleResponse;
import com.absolutely.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<GameSimpleResponse> get(@RequestParam boolean isAll )
    {
        if (isAll)
            return gameService.getAllGames();
        else return gameService.getOpenGames();
    }

    @RequestMapping(
            method=RequestMethod.POST,
            path="/game/{gameId}/connect",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<?> connectToGame(@RequestBody ConnectRequest connectRequest, @PathVariable(name="gameId") Long id){
        GamesEntity gamesToConnect = gameService.getGamesById(id);
        if (!connectRequest.getName().equals("") && !connectRequest.getName().equals(gamesToConnect.getFirstPlayer())) {
                GamesEntity gameEdited = gamesToConnect.edit(connectRequest);
                return ResponseEntity.ok(gameService.connect(gameEdited));
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(
            method=RequestMethod.POST,
            path="/game/create",
            consumes="application/json",
            produces="application/json"
    )
    public ResponseEntity<?> createGame(@RequestBody Map<String, String> request){
        if (!request.get("name").equals(""))
        return ResponseEntity.ok(gameService.addGame(request.get("name")));
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
