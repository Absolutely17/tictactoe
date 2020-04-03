package com.absolutely.tictactoe.controller;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.request.MoveRequest;
import com.absolutely.tictactoe.response.GameSimpleResponse;
import com.absolutely.tictactoe.response.MoveResponse;
import com.absolutely.tictactoe.service.GameService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(
            method = RequestMethod.GET,
            path="/game/{id}/state",
            produces="application/json"
    )
    public GameSimpleResponse getState(@PathVariable(name="id") Long id)
    {
        return new GameSimpleResponse(gameService.getGamesById(id));
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

    @RequestMapping(
            method=RequestMethod.POST,
            path="/game/{id}/exit",
            consumes="application/json",
            produces="application/json"
    )
    public GameSimpleResponse exitGame(@PathVariable(name="id") Long id, @RequestBody Map<String, String> request)
    {
        return gameService.exitGame(id, request.get("name"));
    }

    @RequestMapping(
            method=RequestMethod.GET,
            path="/test",
            produces="application/json"
    )
    public TestResponse get()
    {
        Long id = new Long(65);
        byte[] array2 = gameService.getGamesById(id).getCells();
        String array = Base64.encodeBase64String(gameService.getGamesById(id).getCells());
        return new TestResponse(Base64.encodeBase64String(gameService.getGamesById(id).getCells()));
    }
}
class TestResponse{
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public TestResponse()
    {

    }
    public TestResponse(String response)
    {
        this.str=response;
    }
}
