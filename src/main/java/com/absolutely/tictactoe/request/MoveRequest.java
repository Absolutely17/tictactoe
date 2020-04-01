package com.absolutely.tictactoe.request;

public class MoveRequest {
    private Long cell;
    private String name;

    public Long getCell() {
        return cell;
    }

    public void setCell(Long cell) {
        this.cell = cell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
