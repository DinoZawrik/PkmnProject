package ru.mirea.novikovmp.pkmn;

import java.util.Scanner;

public class PkmnApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the card (without extension): ");
        String cardName = scanner.nextLine();

        String filePath = "src/main/resources/" + cardName + ".crd";

        try {
            // Десериализация чужой карты из файла
            Card importedCard = CardImport.deserializeCardFromFile(filePath);

            if (importedCard != null) {
                System.out.println("Imported Card Data:");
                System.out.println(importedCard.toString());
            } else {
                System.out.println("Failed to import card data.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Вывод стека вызовов для более подробной информации об ошибке
        } finally {
            scanner.close();
        }
    }
}