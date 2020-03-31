package com.absolutely.tictactoe.controller;

import com.absolutely.tictactoe.entity.GamesEntity;
import com.absolutely.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private GameService gameService;

    @RequestMapping(
            method= RequestMethod.GET,
            path="/games",
            produces = "application/json"
    )
    public List<GamesEntity> get()
    {
        return gameService.getOpenGames();
    }
}
