package ru.mirea.novikovmp.pkmn;

import java.io.Serializable;
import java.util.List;

public class Card implements Serializable {
    public static final long serialVersionUID = 1L;

    private PokemonStage pokemonStage;
    private String name;
    private int hp;
    private EnergyType energyType;
    private Card evolvesFrom;
    private List<AttackSkill> skills;
    private EnergyType weaknessType;
    private String retreatCost;
    private char regulationMark;
    private Student pokemonOwner;

    public Card(PokemonStage pokemonStage, String name, int hp, EnergyType energyType,
                Card evolvesFrom, List<AttackSkill> skills, EnergyType weaknessType,
                String retreatCost, char regulationMark, Student pokemonOwner) {
        this.pokemonStage = pokemonStage;
        this.name = name;
        this.hp = hp;
        this.energyType = energyType;
        this.evolvesFrom = evolvesFrom;
        this.skills = skills;
        this.weaknessType = weaknessType;
        this.retreatCost = retreatCost;
        this.regulationMark = regulationMark;
        this.pokemonOwner = pokemonOwner;
    }

    // Геттеры
    public PokemonStage getPokemonStage() {
        return pokemonStage;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public Card getEvolvesFrom() {
        return evolvesFrom;
    }

    public List<AttackSkill> getSkills() {
        return skills;
    }

    public EnergyType getWeaknessType() {
        return weaknessType;
    }

    public String getRetreatCost() {
        return retreatCost;
    }

    public char getRegulationMark() {
        return regulationMark;
    }

    public Student getPokemonOwner() {
        return pokemonOwner;
    }

    // Сеттеры
    public void setPokemonStage(PokemonStage pokemonStage) {
        this.pokemonStage = pokemonStage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public void setEvolvesFrom(Card evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public void setSkills(List<AttackSkill> skills) {
        this.skills = skills;
    }

    public void setWeaknessType(EnergyType weaknessType) {
        this.weaknessType = weaknessType;
    }

    public void setRetreatCost(String retreatCost) {
        this.retreatCost = retreatCost;
    }

    public void setRegulationMark(char regulationMark) {
        this.regulationMark = regulationMark;
    }

    public void setPokemonOwner(Student pokemonOwner) {
        this.pokemonOwner = pokemonOwner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Card Information:\n");
        sb.append("  Pokemon Stage: ").append(pokemonStage).append("\n");
        sb.append("  Name: ").append(name).append("\n");
        sb.append("  HP: ").append(hp).append("\n");
        sb.append("  Energy Type: ").append(energyType).append("\n");

        if (evolvesFrom != null) {
            sb.append("\nEvolution From:\n");
            String evolutionStr = evolvesFrom.getFormattedToString();
            String[] lines = evolutionStr.split("\n");
            for (String line : lines) {
                sb.append("  ").append(line).append("\n");
            }
        }

        sb.append("\nSkills:\n");
        for (AttackSkill skill : skills) {
            String skillStr = skill.toString();
            String[] lines = skillStr.split("\n");
            for (String line : lines) {
                sb.append("  ").append(line).append("\n");
            }
        }

        sb.append("\nWeakness Type: ").append(weaknessType != null ? weaknessType : "None").append("\n");
        sb.append("  Retreat Cost: '").append(retreatCost).append("'\n");
        sb.append("  Regulation Mark: ").append(regulationMark).append("\n");
        sb.append("  Pokemon Owner:\n");
        String ownerStr = pokemonOwner.toString();
        String[] ownerLines = ownerStr.split("\n");
        for (String line : ownerLines) {
            sb.append("    ").append(line).append("\n");
        }

        return sb.toString();
    }

    public String getFormattedToString() {
        String toString = this.toString();
        int index = toString.indexOf("Card Information:");
        if (index != -1) {
            return toString.substring(0, index) + "\n  " + toString.substring(index);
        } else {
            return toString;
        }
    }
}