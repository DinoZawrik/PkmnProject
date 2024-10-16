package ru.mirea.novikovmp.pkmn;

public enum EnergyType {
    Fire("F"),
    Grass("G"),
    Water("W"),
    Lightning("L"),
    Psychic("P"),
    Fighting("H"), // Fighting обозначается как H
    Darkness("D"),
    Metal("M"),
    Fairy("I"), // Fairy обозначается как I
    Dragon("R"), // Dragon обозначается как R
    Colorless("C");

    private final String symbol;

    EnergyType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name();
    }
}