package com.narxoz.rpg.arena;

import java.util.ArrayList;
import java.util.List;

public class TournamentResult {
    private String winner;
    private int rounds;
    private List<String> log;

    public TournamentResult() {
        this.log = new ArrayList<>();
    }

    public void addLogEntry(String entry) {
        log.add(entry);
    }

    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }
    public int getRounds() { return rounds; }
    public void setRounds(int rounds) { this.rounds = rounds; }
    public List<String> getLog() { return log; }
}