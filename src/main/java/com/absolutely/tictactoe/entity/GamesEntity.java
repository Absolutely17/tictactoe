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
    public GamesEntity()
    { }
    public GamesEntity(String name)
    {
        this.firstPlayer=name;
        this.secondPlayer=null;
        this.opened=true;
    }

    public GamesEntity edit(ConnectRequest connectRequest)
    {
        if (connectRequest.getName()!=null)
            this.secondPlayer=connectRequest.getName();
        this.opened=false;
        return this;
    }
}
