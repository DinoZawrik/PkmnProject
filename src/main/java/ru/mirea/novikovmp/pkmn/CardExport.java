package ru.mirea.novikovmp.pkmn;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CardExport {

    public static void exportCardToFile(Card card,String filePath){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(card);
        }catch(Exception e){
            System.err.println("Error exporting card to file: "+e.getMessage());
        }
    }
}