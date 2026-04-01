package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;

public class AttackCommand implements ActionCommand {
    private ArenaFighter hero;
    private ArenaOpponent opponent;
    private int attackPower;
    private int damageDealt;

    public AttackCommand(ArenaFighter hero, ArenaOpponent opponent, int attackPower) {
        this.hero = hero;
        this.opponent = opponent;
        this.attackPower = attackPower;
        this.damageDealt = 0;
    }

    @Override
    public void execute() {
        int initialHp = opponent.getHealth();
        opponent.takeDamage(attackPower);
        damageDealt = initialHp - opponent.getHealth();
    }

    @Override
    public void undo() {
        if (damageDealt > 0) {
            opponent.heal(damageDealt);
        }
    }

    @Override
    public String getDescription() {
        return hero.getName() + " attacks " + opponent.getName() +
                " for " + attackPower + " damage";
    }
}