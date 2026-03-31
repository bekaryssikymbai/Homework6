package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class BlockHandler extends DefenseHandler {
    private double blockPercent;

    public BlockHandler(double blockPercent) {
        this.blockPercent = blockPercent;
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        int reducedDamage = (int)(incomingDamage * (1 - blockPercent));
        if (reducedDamage < incomingDamage) {
            System.out.println(target.getName() + " blocks, reducing damage from " +
                    incomingDamage + " to " + reducedDamage);
        }
        passToNext(reducedDamage, target);
    }
}