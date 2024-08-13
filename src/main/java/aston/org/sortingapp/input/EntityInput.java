package aston.org.sortingapp.input;

import aston.org.sortingapp.models.Animal;
import aston.org.sortingapp.models.Barrel;
import aston.org.sortingapp.models.Human;
import java.util.Scanner;

public abstract class EntityInput<T> {

    protected T[] array;
    protected Class<T> entityType;
    protected Scanner scan;

    protected EntityInput(Class<T> entityType) {
        this.entityType = entityType;
        scan = new Scanner(System.in);
    }

    protected T createInstance() {
        if (entityType == Animal.class) {
            return entityType.cast(animalEntry());
        } else if (entityType == Barrel.class) {
            return entityType.cast(barrelEntry());
        } else if (entityType == Human.class) {
            return entityType.cast(humanEntry());
        }
        return null;
    }

    protected abstract Animal animalEntry();
    protected abstract Barrel barrelEntry();
    protected abstract Human humanEntry();

    public abstract void performInput();

    public void printArray() {
        for (int i=0; i<array.length; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public T[] getArray() {
        return array;
    }

}
