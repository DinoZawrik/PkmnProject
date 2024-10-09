package ru.mirea.novikovmp.pkmn;

public enum PokemonStage {
    Basic,
    Stage1,
    Stage2,
    VStar,
    VMax;

    PokemonStage() {
    }

    @Override
    public String toString() {
        return "PokemonStage{}";
    }
}
