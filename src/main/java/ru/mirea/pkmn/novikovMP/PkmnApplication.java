package ru.mirea.pkmn.novikovMP;

import ru.mirea.pkmn.*;

import java.io.Serializable;

public class PkmnApplication implements Serializable {
    public static final long serialVersionUID = 1L;

    private static final String CARD_FILE_PATH = "src/main/resources/my_card.txt";
    private static final String STARAPTOR_FILE_PATH = "Staraptor.crd";
    private static final String SOMEONE_FILE_PATH = "src/main/resources/Corvisquire.crd";

    public static void main(String[] args) {
        Card card = loadCardFromTextFile(CARD_FILE_PATH);
        if (card != null) {
            printCardInfo(card);
            saveCard(card);
            saveEvolutionCard(card);
        }

        Card myLoadedCard = deserializeCard(STARAPTOR_FILE_PATH);
        if (myLoadedCard != null) {
            printDeserializedCardInfo(myLoadedCard, "моей карты");
        }

        Card loadedCard = deserializeCard(SOMEONE_FILE_PATH);
        if (loadedCard != null) {
            printDeserializedCardInfo(loadedCard, "карты одногруппника");
        }
    }

    private static Card loadCardFromTextFile(String filePath) {
        CardImport cardImport = new CardImport(filePath);
        return cardImport.loadCard();
    }

    private static void printCardInfo(Card card) {
        System.out.println("Вывод из текстового файла:");
        System.out.println(card);

        if (card.getEvolvesFrom() != null) {
            System.out.println("Эволюционирует из:");
            System.out.println(card.getEvolvesFrom());
        }
    }

    private static void saveCard(Card card) {
        CardExport cardExport = new CardExport();
        cardExport.saveCard(card);
    }

    private static void saveEvolutionCard(Card card) {
        if (card.getEvolvesFrom() != null) {
            CardExport evolutionExport = new CardExport();
            evolutionExport.saveCard(card.getEvolvesFrom());
        }
    }

    private static Card deserializeCard(String filePath) {
        CardImport myCardImport = new CardImport(filePath);
        return myCardImport.deserializeCard(filePath);
    }

    private static void printDeserializedCardInfo(Card deserializedCard, String cardType) {
        System.out.println("Десериализация " + cardType + " (проверка корректности):");
        System.out.println(deserializedCard);
    }
}