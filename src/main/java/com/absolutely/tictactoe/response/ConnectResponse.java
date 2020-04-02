package com.absolutely.tictactoe.response;

import com.absolutely.tictactoe.entity.GamesEntity;

public class ConnectResponse {
    private Boolean successful;
    private GameSimpleResponse game;

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public GameSimpleResponse getGame() {
        return game;
    }

    public void setGame(GameSimpleResponse game) {
        this.game = game;
    }

    public ConnectResponse(GamesEntity game) {
        this.game = new GameSimpleResponse(game);
        this.successful=true;
    }
}
