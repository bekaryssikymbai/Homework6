package com.narxoz.rpg.arena;

public class ArenaOpponent {
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;

    public ArenaOpponent(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttackPower() { return attackPower; }

    public int getMaxHealth() {
        return maxHealth;
    }
}