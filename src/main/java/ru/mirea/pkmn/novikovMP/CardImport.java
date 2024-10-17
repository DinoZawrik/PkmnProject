package ru.mirea.pkmn.novikovMP;

import ru.mirea.pkmn.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CardImport {
    private static final String FILE_NOT_FOUND_ERROR = "Файл не найден: ";
    private static final String UNKNOWN_ENERGY_TYPE_ERROR = "Неизвестный тип энергии: ";
    private static final String INVALID_ABILITY_FORMAT_ERROR = "Неправильный формат способности: ";
    private static final String UNKNOWN_WEAKNESS_TYPE_ERROR = "Неизвестный тип слабости: ";
    private static final String UNKNOWN_RESISTANCE_TYPE_ERROR = "Неизвестный тип сопротивления: ";

    private String filePath;

    public CardImport(String filePath) {
        this.filePath = filePath;
    }

    public Card loadCard() {
        return loadCardFromFile(filePath);
    }

    private Card loadCardFromFile(String filePath) {
        Card card = new Card();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            if (!fileExists(filePath)) {
                throw new FileNotFoundException(FILE_NOT_FOUND_ERROR + filePath);
            }

            card.setPokemonStage(parsePokemonStage(br.readLine()));
            card.setName(br.readLine());
            card.setHp(Integer.parseInt(br.readLine()));

            card.setPokemonType(parseEnergyType(br.readLine()));
            card.setEvolvesFrom(loadEvolvesFrom(br.readLine()));

            card.setSkills(parseSkills(br.readLine()));
            card.setWeaknessType(parseWeaknessType(br.readLine()));
            card.setResistanceType(parseResistanceType(br.readLine()));

            card.setRetreatCost(br.readLine());
            card.setGameSet(br.readLine());
            card.setRegulationMark(br.readLine().trim().charAt(0));

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return card;
    }

    private boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    private PokemonStage parsePokemonStage(String stageStr) {
        return PokemonStage.valueOf(stageStr.trim().toUpperCase());
    }

    private EnergyType parseEnergyType(String energyTypeStr) {
        try {
            return EnergyType.valueOf(energyTypeStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println(UNKNOWN_ENERGY_TYPE_ERROR + energyTypeStr);
            return null;
        }
    }

    private Card loadEvolvesFrom(String evolvesFromPath) {
        if (evolvesFromPath.equals("-")) {
            return null;
        }
        return loadCardFromFile(evolvesFromPath);
    }

    private List<AttackSkill> parseSkills(String skillsStr) {
        List<AttackSkill> skills = new ArrayList<>();
        String[] abilitiesData = skillsStr.split(",");
        for (String ability : abilitiesData) {
            String[] abilityParts = ability.split("/");
            if (abilityParts.length < 3) {
                System.err.println(INVALID_ABILITY_FORMAT_ERROR + ability);
                continue;
            }
            skills.add(new AttackSkill(
                    abilityParts[1].trim(),
                    "",
                    abilityParts[0].trim(),
                    Integer.parseInt(abilityParts[2].trim())
            ));
        }
        return skills;
    }

    private EnergyType parseWeaknessType(String weaknessLine) {
        if (weaknessLine.equals("NO") || weaknessLine.equals("-") || weaknessLine.isEmpty()) {
            return null;
        }
        try {
            return EnergyType.valueOf(weaknessLine.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println(UNKNOWN_WEAKNESS_TYPE_ERROR + weaknessLine);
            return null;
        }
    }

    private EnergyType parseResistanceType(String resistanceLine) {
        if (resistanceLine.equals("NO") || resistanceLine.equals("-") || resistanceLine.isEmpty()) {
            return null;
        }
        try {
            return EnergyType.valueOf(resistanceLine.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println(UNKNOWN_RESISTANCE_TYPE_ERROR + resistanceLine);
            return null;
        }
    }

    public Card deserializeCard(String filePath) {
        Card card = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            card = (Card) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при десериализации.");
            e.printStackTrace();
        }
        return card;
    }
}