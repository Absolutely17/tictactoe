package com.absolutely.tictactoe.response;

import com.absolutely.tictactoe.entity.GamesEntity;
import org.apache.tomcat.util.codec.binary.Base64;

public class StateResponse {
    private Long id;
    private Boolean opened;
    private String firstPlayer;
    private String secondPlayer;
    private String winner;
    private String currentMove;
    private int lastMove;
    private Boolean isClosedByPlayer;
    private String squares;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(String currentMove) {
        this.currentMove = currentMove;
    }

    public int getLastMove() {
        return lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    public Boolean getClosedByPlayer() {
        return isClosedByPlayer;
    }

    public void setClosedByPlayer(Boolean closedByPlayer) {
        isClosedByPlayer = closedByPlayer;
    }

    public String getSquares() {
        return squares;
    }

    public void setSquares(String squares) {
        this.squares = squares;
    }
    public StateResponse(GamesEntity game)
    {
        this.id=game.getId();
        this.currentMove=game.getCurrentMove();
        this.firstPlayer=game.getFirstPlayer();
        this.opened=game.getOpened();
        this.secondPlayer=game.getSecondPlayer();
        this.winner=game.getWinner();
        this.lastMove=game.getLastMove();
        this.isClosedByPlayer=game.getClosedByPlayer();
        this.squares= Base64.encodeBase64String(game.getCells());
    }
}
