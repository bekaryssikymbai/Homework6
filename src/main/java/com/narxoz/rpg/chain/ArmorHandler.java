package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class ArmorHandler extends DefenseHandler {
    private int armorValue;

    public ArmorHandler(int armorValue) {
        this.armorValue = armorValue;
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        int reducedDamage = Math.max(0, incomingDamage - armorValue);
        if (reducedDamage < incomingDamage) {
            System.out.println(target.getName() + "'s armor absorbs " +
                    (incomingDamage - reducedDamage) + " damage");
        }
        passToNext(reducedDamage, target);
    }
}