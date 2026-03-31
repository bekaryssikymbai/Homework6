package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class DodgeHandler extends DefenseHandler {
    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        double dodgeChance = target.getDodgeChance();
        double roll = Math.random();

        if (roll < dodgeChance) {
            System.out.println(target.getName() + " dodges the attack!");
            return;
        }

        passToNext(incomingDamage, target);
    }
}