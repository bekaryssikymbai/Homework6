package com.narxoz.rpg;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;
import com.narxoz.rpg.command.ActionQueue;
import com.narxoz.rpg.command.AttackCommand;
import com.narxoz.rpg.command.DefendCommand;
import com.narxoz.rpg.command.HealCommand;

public class TestCommand {
    public static void main(String[] args) {
        ArenaFighter hero = new ArenaFighter("Hero", 100, 3, 0.2, 30, 5);
        ArenaOpponent opponent = new ArenaOpponent("Enemy", 50, 15);

        ActionQueue queue = new ActionQueue();

        queue.enqueue(new AttackCommand(hero, opponent, 20));
        queue.enqueue(new HealCommand(hero, 25));
        queue.enqueue(new DefendCommand(hero, 0.15));

        System.out.println("Queue size: " + queue.size());
        queue.undoLast();
        System.out.println("After undo: " + queue.size());

        System.out.println("Before - Hero HP: " + hero.getHealth());
        queue.executeAll();
        System.out.println("After - Hero HP: " + hero.getHealth());
    }
}