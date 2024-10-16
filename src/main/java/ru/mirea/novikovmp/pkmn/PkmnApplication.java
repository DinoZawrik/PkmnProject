package ru.mirea.novikovmp.pkmn;

import java.io.FileNotFoundException;

public class PkmnApplication {

    public static void main(String[] args){
        String filePath="src/main/resources/my_card.txt"; // Путь к вашему файлу my_card.txt
        String exportFilePath="src/main/resources/"+"Staraptor.crd"; // Путь для экспорта сериализованной карты

        try{
// Создание экземпляра класса CardImport
            Card card=CardImport.importCardFromFile(filePath);

            if(card!=null){
                System.out.println("Loaded Card Data:");
                System.out.println(card.toString());

// Экспорт карты в файл
                CardExport.exportCardToFile(card,exportFilePath);

                System.out.println("Card exported to file: "+exportFilePath);

// Десериализация карты из файла
                Card importedCard=CardImport.deserializeCardFromFile(exportFilePath);

                if(importedCard!=null){
                    System.out.println("Imported Card Data:");
                    System.out.println(importedCard.toString());
                }else{
                    System.out.println("Failed to import card data.");
                }
            }else{
                System.out.println("Failed to load card data.");
            }
        }catch(FileNotFoundException e){
            System.err.println("File not found: "+filePath);
        }catch(Exception e){
            System.err.println("An error occurred: "+e.getMessage());
        }
    }
}