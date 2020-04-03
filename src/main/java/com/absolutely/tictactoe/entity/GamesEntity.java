package com.absolutely.tictactoe.entity;

import com.absolutely.tictactoe.request.ConnectRequest;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;


@Entity
@Table(name = "games")
public class GamesEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "opened", nullable = false)
    private Boolean opened;

    @Column(name = "firstPlayer")
    private String firstPlayer;

    @Column(name = "secondPlayer")
    private String secondPlayer;

    @Column(name = "winner")
    private String winner;

    @Column(name = "currentMove")
    private String currentMove;

    @Column(name ="cells", length = 10000, columnDefinition = "BLOB")
    private byte[] cells;

    @Column(name = "lastMove")
    private int lastMove;

    @Column(name = "closedByPlayer")
    private boolean closedByPlayer;

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

    public byte[] getCells() {
        return cells;
    }

    public void setCells(byte[] cells) {
        this.cells = cells;
    }

    public boolean getClosedByPlayer() {
        return closedByPlayer;
    }

    public void setClosedByPlayer(boolean closedByPlayer) {
        this.closedByPlayer = closedByPlayer;
    }

    public GamesEntity()
    { }
    public GamesEntity(String name)
    {
        this.firstPlayer=name;
        this.secondPlayer=null;
        this.opened=true;
        this.winner=null;
        this.currentMove=null;
        this.cells = new byte[361];
        this.lastMove=-1;
        this.closedByPlayer =false;
    }

    public GamesEntity edit(ConnectRequest connectRequest)
    {
        if (connectRequest.getName()!=null)
            this.secondPlayer=connectRequest.getName();
        this.opened=false;
        this.currentMove=this.firstPlayer;
        return this;
    }
}
