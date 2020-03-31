package com.absolutely.tictactoe.response;

import com.absolutely.tictactoe.entity.GamesEntity;

public class ConnectResponse {
    private Boolean successful;
    private GamesEntity game;

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }

    public ConnectResponse(GamesEntity game) {
        this.game = game;
        this.successful=true;
    }
}
