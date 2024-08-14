package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class InputFromFile<T> extends EntityInput<T> {

    public InputFromFile(Class<T> entityType) {
        super(entityType);
    }

    @Override
    public void performInput() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
