package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

public class InputFromFile<T> extends AbstractInputMethod<T> {

    public InputFromFile(Class<T> entityType) {
        super(entityType);
    }

    @Override
    public boolean createArray() {
        String fileName;
        if (entityType == Animal.class) {
            fileName = "Animal.data";
        } else if (entityType == Barrel.class) {
            fileName = "Barrel.data";
        } else if (entityType == Human.class) {
            fileName = "Human.data";
        } else {
            fileName = "Other.data";
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            array = (T[]) in.readObject();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Отсутствует файл с данными!");;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected <R> T read(Class<T> type, R[] randValues) {
        // The array has already been initialized in the 'createArray' method
        return null;
    }

}
