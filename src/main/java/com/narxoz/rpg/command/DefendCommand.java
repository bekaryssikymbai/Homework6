package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class DefendCommand implements ActionCommand {
    private ArenaFighter hero;
    private double dodgeBoost;

    public DefendCommand(ArenaFighter hero, double dodgeBoost) {
        this.hero = hero;
        this.dodgeBoost = dodgeBoost;
    }

    @Override
    public void execute() {
        hero.modifyDodgeChance(dodgeBoost);
    }

    @Override
    public void undo() {
        hero.modifyDodgeChance(-dodgeBoost);
    }

    @Override
    public String getDescription() {
        return hero.getName() + " defends, increasing dodge chance by " +
                (dodgeBoost * 100) + "%";
    }
}