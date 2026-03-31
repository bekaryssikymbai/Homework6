package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class HpHandler extends DefenseHandler {
    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        if (incomingDamage > 0) {
            System.out.println(target.getName() + " takes " + incomingDamage + " damage!");
            target.takeDamage(incomingDamage);
        }
    }
}