package ru.mirea.novikovmp.pkmn;

import java.io.FileNotFoundException;

public class PkmnApplication {

    public static void main(String[] args) {
        String filePath = "src/main/resources/my_card.txt"; // Путь к вашему файлу my_card.txt
        String exportFilePath = "src/main/resources/card.crd"; // Путь для экспорта сериализованной карты

        try {
            // Создание экземпляра класса CardImport
            CardImport importer = new CardImport();

            // Загрузка данных из файла
            Card card = importer.importCardFromFile(filePath);

            if (card != null) {
                System.out.println("Loaded Card Data:");
                System.out.println(card.toString());

                // Экспорт карты в файл
                CardExport exporter = new CardExport();
                exporter.exportCardToFile(card, exportFilePath);

                System.out.println("Card exported to file: " + exportFilePath);

                // Импорт карты из файла
                Card importedCard = exporter.importCardFromFile(exportFilePath);

                if (importedCard != null) {
                    System.out.println("Imported Card Data:");
                    System.out.println(importedCard.toString());
                } else {
                    System.out.println("Failed to import card data.");
                }
            } else {
                System.out.println("Failed to load card data.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}