package ru.mirea.novikovmp.pkmn;

import java.io.Serializable;

public class AttackSkill implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int damage;
    private String cost;
    private String description;

    public AttackSkill() {}

    public AttackSkill(String name, int damage, String cost, String description) {
        this.name = name;
        this.damage = damage;
        this.cost = cost;
        this.description = description;
    }

    @Override
    public String toString() {
        return "AttackSkill{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", cost='" + cost + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public String getCost() { return cost; }
    public void setCost(String cost) { this.cost = cost; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}