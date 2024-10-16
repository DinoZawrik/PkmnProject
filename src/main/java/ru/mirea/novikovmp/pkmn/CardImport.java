package ru.mirea.novikovmp.pkmn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardImport {

    public static Card importCardFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        try {
            // Чтение стадии покемона
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            PokemonStage pokemonStage;
            String stageStr = scanner.nextLine().trim();
            try {
                pokemonStage = PokemonStage.valueOf(stageStr.replace(" ", ""));
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid stage: " + stageStr);
                return null; // или любое другое значение по умолчанию
            }

            // Чтение имени покемона
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            String name = scanner.nextLine().trim();

            // Чтение очков здоровья покемона
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            int hp;
            try {
                hp = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Invalid HP value: " + scanner.nextLine());
                hp = 0; // Установка дефолтного значения для HP, если оно не указано явно
            }

            // Чтение типа покемона
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            EnergyType energyType;
            try {
                energyType = EnergyType.valueOf(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid energy type: " + scanner.nextLine());
                return null; // или любое другое значение по умолчанию
            }

            // Чтение предыдущей эволюции
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            String evolvesFromPath = scanner.nextLine().trim();
            Card evolvesFrom = null;
            if (!evolvesFromPath.equals("-")) {
                try {
                    evolvesFrom = importCardFromFile(evolvesFromPath);
                } catch (FileNotFoundException e) {
                    System.err.println("File not found: " + evolvesFromPath);
                }
            }

            // Чтение способностей атаки
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            List<AttackSkill> skills = new ArrayList<>();
            String[] skillsData = scanner.nextLine().split(",");
            for (String skillData : skillsData) {
                String[] parts = skillData.split("/");
                if (parts.length < 3) {
                    System.err.println("Invalid skill data: " + skillData);
                    continue;
                }
                String cost = parts[0];
                String skillName = parts[1];
                String damageOrAction; // Объявление переменной
                String description;

                // Проверяем, является ли третья часть числом или описанием действия
                if (parts.length > 3) {
                    // Если третья часть не число, то она является частью описания действия
                    damageOrAction = String.join("/", java.util.Arrays.copyOfRange(parts, 2, parts.length));
                    description = "";
                } else {
                    try {
                        // Попытка парсинга третьей части как числа
                        damageOrAction = parts[2];
                        description = "";
                    } catch (NumberFormatException e) {
                        // Если ошибка парсинга, то третья часть является описанием действия
                        damageOrAction = parts[2];
                        description = "";
                    }
                }

                // Если damageOrAction содержит только число, то оно является уроном
                int damage;
                try {
                    damage = Integer.parseInt(damageOrAction);
                } catch (NumberFormatException e) {
                    damage = 0; // Установка дефолтного значения для урона, если оно не указано явно
                }

                skills.add(new AttackSkill(skillName, damage, cost, damageOrAction + " " + description));
            }

            // Чтение типов слабости
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            EnergyType weaknessType;
            String weaknessTypeStr = scanner.nextLine().trim();
            if (!weaknessTypeStr.isEmpty()) {
                try {
                    weaknessType = EnergyType.valueOf(weaknessTypeStr);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid weakness type: " + weaknessTypeStr);
                    weaknessType = null;
                }
            } else {
                weaknessType = null;
            }

            // Чтение цены побега
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            String retreatCost = scanner.nextLine().trim();

            // Чтение дополнительных полей
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }

            char regulationMark;
            try {
                regulationMark = scanner.nextLine().trim().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.err.println("Invalid regulation mark: " + scanner.nextLine());
                regulationMark = ' '; // или любое другое значение по умолчанию
            }

            // Чтение владельца карты
            if (!scanner.hasNextLine()) {
                System.err.println("Not enough data in file: " + filePath);
                return null;
            }
            String[] ownerData = scanner.nextLine().split("/");
            Student pokemonOwner;
            if (ownerData.length < 4 || ownerData[0].equals("-")) {
                // Используем дефолтное значение для владельца если данные неверны
                pokemonOwner = new Student("Default", "Default", "Default", "Default");
            } else {
                pokemonOwner = new Student(ownerData[0], ownerData[1], ownerData[2], ownerData[3]);
            }

            // Создание экземпляра класса Card
            return new Card(pokemonStage, name, hp, energyType, evolvesFrom, skills, weaknessType, retreatCost, regulationMark, pokemonOwner);

        } finally {
            scanner.close();
        }
    }

    public static Card deserializeCardFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Card) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error deserializing card from file: " + e.getMessage());
            return null;
        }
    }
}