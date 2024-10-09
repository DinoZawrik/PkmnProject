package ru.mirea.novikovmp.pkmn;

public enum EnergyType {
    Fire,
    Grass,
    Water,
    Lightning,
    Psychic,
    Fighting,
    Darkness,
    Metal,
    Fairy,
    Dragon,
    Colorless;

    EnergyType() {
    }

    @Override
    public String toString() {
        return "EnergyType{}";
    }
}
