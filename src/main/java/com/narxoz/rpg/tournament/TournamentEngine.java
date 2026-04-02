package com.narxoz.rpg.tournament;

import com.narxoz.rpg.arena.*;
import com.narxoz.rpg.command.*;
import com.narxoz.rpg.chain.*;

public class TournamentEngine {

    public TournamentResult runTournament(ArenaFighter hero, ArenaOpponent opponent) {
        TournamentResult result = new TournamentResult();
        ActionQueue actionQueue = new ActionQueue();

        DefenseHandler defenseChain = buildDefenseChain(hero);

        int round = 1;
        int maxRounds = 20;

        while (hero.isAlive() && opponent.isAlive() && round <= maxRounds) {
            result.addLogEntry("=== Round " + round + " ===");

            actionQueue.enqueue(new AttackCommand(hero, opponent, 15));
            if (hero.getPotions() > 0 && hero.getHealth() < hero.getMaxHealth() * 0.5) {
                actionQueue.enqueue(new HealCommand(hero, 20));
            }
            if (hero.getDodgeChance() < 0.5) {
                actionQueue.enqueue(new DefendCommand(hero, 0.2));
            }

            result.addLogEntry("Queued actions:");
            for (String desc : actionQueue.getCommandDescriptions()) {
                result.addLogEntry("  - " + desc);
            }

            actionQueue.executeAll();

            result.addLogEntry(hero.getName() + " HP: " + hero.getHealth() + "/" + hero.getMaxHealth());
            result.addLogEntry(opponent.getName() + " HP: " + opponent.getHealth() + "/" + opponent.getMaxHealth());

            if (!opponent.isAlive()) {
                result.addLogEntry(opponent.getName() + " has been defeated!");
                break;
            }

            result.addLogEntry(opponent.getName() + " attacks " + hero.getName() + "!");
            defenseChain.handle(opponent.getAttackPower(), hero);

            result.addLogEntry(hero.getName() + " HP: " + hero.getHealth() + "/" + hero.getMaxHealth());

            if (!hero.isAlive()) {
                result.addLogEntry(hero.getName() + " has been defeated!");
                break;
            }

            round++;
        }

        if (hero.isAlive()) {
            result.setWinner(hero.getName());
        } else {
            result.setWinner(opponent.getName());
        }
        result.setRounds(round);

        return result;
    }

    private DefenseHandler buildDefenseChain(ArenaFighter hero) {
        DefenseHandler dodgeHandler = new DodgeHandler();
        DefenseHandler blockHandler = new BlockHandler(hero.getBlockRating() / 100.0);
        DefenseHandler armorHandler = new ArmorHandler(hero.getArmor());
        DefenseHandler hpHandler = new HpHandler();

        dodgeHandler.setNext(blockHandler)
                .setNext(armorHandler)
                .setNext(hpHandler);

        return dodgeHandler;
    }
}