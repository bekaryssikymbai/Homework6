package com.narxoz.rpg;



import com.narxoz.rpg.arena.*;
import com.narxoz.rpg.command.*;
import com.narxoz.rpg.chain.*;
import com.narxoz.rpg.tournament.TournamentEngine;

public class Main {
    public static void main(String[] args) {
        System.out.println("=" .repeat(60));
        System.out.println("RPG Grand Arena Tournament Demo");
        System.out.println("=" .repeat(60));

        System.out.println("\n1. COMMAND PATTERN DEMO");
        System.out.println("-".repeat(40));
        commandPatternDemo();

        System.out.println("\n2. CHAIN OF RESPONSIBILITY DEMO");
        System.out.println("-".repeat(40));
        chainOfResponsibilityDemo();

        System.out.println("\n3. FULL TOURNAMENT DEMO");
        System.out.println("-".repeat(40));
        fullTournamentDemo();
    }

    private static void commandPatternDemo() {
        ArenaFighter hero = new ArenaFighter("Aragorn", 100, 3, 0.2, 30, 5);
        ArenaOpponent opponent = new ArenaOpponent("Uruk-hai", 50, 15);

        ActionQueue queue = new ActionQueue();

        System.out.println("Creating actions:");
        AttackCommand attack = new AttackCommand(hero, opponent, 20);
        HealCommand heal = new HealCommand(hero, 25);
        DefendCommand defend = new DefendCommand(hero, 0.15);

        System.out.println("  - " + attack.getDescription());
        System.out.println("  - " + heal.getDescription());
        System.out.println("  - " + defend.getDescription());

        queue.enqueue(attack);
        queue.enqueue(heal);
        queue.enqueue(defend);

        System.out.println("\nQueue size: " + queue.size());
        System.out.println("Queued actions:");
        for (String desc : queue.getCommandDescriptions()) {
            System.out.println("  - " + desc);
        }

        System.out.println("\nUndoing last action...");
        queue.undoLast();
        System.out.println("Queue size after undo: " + queue.size());
        System.out.println("Remaining actions:");
        for (String desc : queue.getCommandDescriptions()) {
            System.out.println("  - " + desc);
        }

        System.out.println("\nExecuting remaining actions:");
        System.out.println("Before - Hero HP: " + hero.getHealth() +
                ", Opponent HP: " + opponent.getHealth());
        queue.executeAll();
        System.out.println("After - Hero HP: " + hero.getHealth() +
                ", Opponent HP: " + opponent.getHealth());
    }

    private static void chainOfResponsibilityDemo() {
        ArenaFighter hero = new ArenaFighter("Legolas", 80, 2, 0.3, 25, 8);

        System.out.println("Initial hero state:");
        System.out.println("  Name: " + hero.getName());
        System.out.println("  HP: " + hero.getHealth());
        System.out.println("  Dodge Chance: " + (hero.getDodgeChance() * 100) + "%");
        System.out.println("  Block Rating: " + hero.getBlockRating() + "%");
        System.out.println("  Armor: " + hero.getArmor());

        DefenseHandler dodgeHandler = new DodgeHandler();
        DefenseHandler blockHandler = new BlockHandler(hero.getBlockRating() / 100.0);
        DefenseHandler armorHandler = new ArmorHandler(hero.getArmor());
        DefenseHandler hpHandler = new HpHandler();

        dodgeHandler.setNext(blockHandler)
                .setNext(armorHandler)
                .setNext(hpHandler);

        int attackDamage = 25;
        System.out.println("\nProcessing " + attackDamage + " damage attack:");
        System.out.println("-".repeat(30));

        for (int i = 1; i <= 3; i++) {
            System.out.println("\nAttack " + i + ":");
            int initialHp = hero.getHealth();
            dodgeHandler.handle(attackDamage, hero);
            int finalHp = hero.getHealth();
            System.out.println("  Damage taken: " + (initialHp - finalHp));
            System.out.println("  Remaining HP: " + hero.getHealth());

            if (i < 3) {
                hero.takeDamage(-hero.getHealth());
            }
        }
    }

    private static void fullTournamentDemo() {
        ArenaFighter hero = new ArenaFighter("Thorin Oakenshield", 120, 5, 0.25, 35, 10);
        ArenaOpponent opponent = new ArenaOpponent("Azog the Defiler", 100, 18);

        System.out.println("Tournament Participants:");
        System.out.println("  Hero: " + hero.getName() + " (HP: " + hero.getHealth() +
                ", Potions: " + hero.getPotions() + ")");
        System.out.println("  Opponent: " + opponent.getName() + " (HP: " + opponent.getHealth() +
                ", Attack Power: " + opponent.getAttackPower() + ")");

        TournamentEngine engine = new TournamentEngine();
        TournamentResult result = engine.runTournament(hero, opponent);

        System.out.println("\nTournament Results:");
        System.out.println("=".repeat(40));
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("\nBattle Log:");
        System.out.println("-".repeat(30));
        for (String logEntry : result.getLog()) {
            System.out.println(logEntry);
        }
    }
}