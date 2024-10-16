package ru.mirea.novikovmp.pkmn;

import java.io.*;

public class CardExport {

    public void exportCardToFile(Card card, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(card);
        } catch (IOException e) {
            System.err.println("Error exporting card to file: " + e.getMessage());
            throw e;
        }
    }

    public Card importCardFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Card) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error importing card from file: " + e.getMessage());
            throw e;
        }
    }
}