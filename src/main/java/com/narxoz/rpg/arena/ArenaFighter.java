package com.narxoz.rpg.arena;

public class ArenaFighter {
    private String name;
    private int health;
    private int maxHealth;
    private int potions;
    private double dodgeChance;
    private int blockRating;
    private int armor;

    public ArenaFighter(String name, int maxHealth, int potions, double dodgeChance,
                        int blockRating, int armor) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.potions = potions;
        this.dodgeChance = dodgeChance;
        this.blockRating = blockRating;
        this.armor = armor;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public void heal(int amount) {
        if (potions > 0) {
            health = Math.min(maxHealth, health + amount);
        }
    }

    public void usePotion() {
        if (potions > 0) {
            potions--;
        }
    }

    public void modifyDodgeChance(double delta) {
        dodgeChance += delta;
        dodgeChance = Math.max(0.0, Math.min(1.0, dodgeChance));
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getPotions() { return potions; }
    public double getDodgeChance() { return dodgeChance; }
    public int getBlockRating() { return blockRating; }
    public int getArmor() { return armor; }
}