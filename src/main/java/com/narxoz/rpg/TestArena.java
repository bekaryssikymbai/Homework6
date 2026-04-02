package com.narxoz.rpg;



import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;
import com.narxoz.rpg.arena.TournamentResult;
import com.narxoz.rpg.tournament.TournamentEngine;

public class TestArena {
    public static void main(String[] args) {
        System.out.println("=== Day 3: Tournament Engine Demo ===\n");

        // Создаем героя
        ArenaFighter hero = new ArenaFighter("Thorin", 120, 5, 0.25, 35, 10);

        // Создаем противника
        ArenaOpponent opponent = new ArenaOpponent("Azog", 100, 18);

        System.out.println("Tournament Participants:");
        System.out.println("  Hero: " + hero.getName() + " (HP: " + hero.getHealth() +
                ", Potions: " + hero.getPotions() + ")");
        System.out.println("  Opponent: " + opponent.getName() + " (HP: " + opponent.getHealth() +
                ", Attack Power: " + opponent.getAttackPower() + ")");

        // Запускаем турнир
        TournamentEngine engine = new TournamentEngine();
        TournamentResult result = engine.runTournament(hero, opponent);

        // Выводим результаты
        System.out.println("\n=== Tournament Results ===");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("\nBattle Log:");
        System.out.println("-".repeat(40));
        for (String log : result.getLog()) {
            System.out.println(log);
        }
    }
}
