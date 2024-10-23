package com.chessgame.chessgame.model;

public class ChessMove {
    private String from;
    private String to;
    private String player;  // Can store who made the move (e.g., "white", "black")

    public ChessMove() {}

    public ChessMove(String from, String to, String player) {
        this.from = from;
        this.to = to;
        this.player = player;
    }

    // Getters and setters
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getPlayer() { return player; }
    public void setPlayer(String player) { this.player = player; }
}
