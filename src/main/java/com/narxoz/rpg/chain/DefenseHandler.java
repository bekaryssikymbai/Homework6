package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public abstract class DefenseHandler {
    protected DefenseHandler next;

    public DefenseHandler setNext(DefenseHandler handler) {
        this.next = handler;
        return handler;
    }

    protected void passToNext(int damage, ArenaFighter target) {
        if (next != null) {
            next.handle(damage, target);
        }
    }

    public abstract void handle(int incomingDamage, ArenaFighter target);
}