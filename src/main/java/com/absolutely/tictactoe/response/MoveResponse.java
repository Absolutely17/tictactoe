package com.absolutely.tictactoe.response;

public class MoveResponse {
    private boolean successful;
    private Long cell;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Long getCell() {
        return cell;
    }

    public void setCell(Long cell) {
        this.cell = cell;
    }

    public MoveResponse(boolean success, Long cell)
    {
        setSuccessful(success);
        setCell(cell);
    }
}
