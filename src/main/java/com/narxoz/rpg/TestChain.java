package com.narxoz.rpg;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.chain.*;

// Временный Main для тестирования
public class TestChain {
    public static void main(String[] args) {
        ArenaFighter hero = new ArenaFighter("TestHero", 100, 3, 0.3, 25, 10);

        DefenseHandler dodge = new DodgeHandler();
        DefenseHandler block = new BlockHandler(0.25);
        DefenseHandler armor = new ArmorHandler(10);
        DefenseHandler hp = new HpHandler();

        dodge.setNext(block).setNext(armor).setNext(hp);

        System.out.println("HP before: " + hero.getHealth());
        dodge.handle(50, hero);
        System.out.println("HP after: " + hero.getHealth());
    }
}