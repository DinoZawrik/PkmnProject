package ru.mirea.pkmn.novikovMP;

import ru.mirea.pkmn.Card;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CardExport {
    public static final long serialVersionUID = 1L;
    private static final String FILE_EXTENSION = ".crd";

    public void saveCard(Card card) {
        if (card == null) {
            System.out.println("Карта не может быть null.");
            return;
        }

        String fileName = getFileName(card);
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(card);
            System.out.println("Карта успешно сохранена в файл: " + fileName + "\n");
        } catch (IOException e) {
            handleIOException(e, fileName);
        }
    }

    private String getFileName(Card card) {
        return card.getName() + FILE_EXTENSION;
    }

    private void handleIOException(IOException e, String fileName) {
        System.out.println("Ошибка при сериализации карты в файл: " + fileName);
        e.printStackTrace();
    }
}