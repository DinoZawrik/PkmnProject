package ru.mirea.novikovmp.pkmn;

import java.io.Serializable;

public class AttackSkill implements Serializable {
    public static final long serialVersionUID = 1L;

    private String name;
    private int damage;
    private String cost;
    private String description;

    public AttackSkill(String name, int damage, String cost, String description) {
        this.name = name;
        this.damage = damage;
        this.cost = cost;
        this.description = description;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AttackSkill{\n" +
                "  name='" + name + "'\n" +
                "  damage=" + damage + "\n" +
                "  cost='" + cost + "'\n" +
                "  description='" + description + "'\n" +
                "}\n";
    }
}