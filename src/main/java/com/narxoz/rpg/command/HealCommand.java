package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class HealCommand implements ActionCommand {
    private ArenaFighter hero;
    private int healAmount;
    private int actualHealApplied;

    public HealCommand(ArenaFighter hero, int healAmount) {
        this.hero = hero;
        this.healAmount = healAmount;
        this.actualHealApplied = 0;
    }

    @Override
    public void execute() {
        if (hero.getPotions() > 0) {
            int initialHp = hero.getHealth();
            hero.heal(healAmount);
            actualHealApplied = hero.getHealth() - initialHp;
            hero.usePotion();
        } else {
            actualHealApplied = 0;
        }
    }

    @Override
    public void undo() {
        if (actualHealApplied > 0) {
            hero.takeDamage(actualHealApplied);
        }
    }

    @Override
    public String getDescription() {
        if (hero.getPotions() > 0) {
            return hero.getName() + " heals for " + healAmount + " HP";
        } else {
            return hero.getName() + " tries to heal but has no potions left!";
        }
    }
}