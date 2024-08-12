package aston.org.sortingapp.input;

import java.util.Scanner;

public abstract class EntityInput<T> {

    protected T[] array;
    protected Class<T> entityType;
    protected Scanner scan;

    protected EntityInput(Class<T> clazz) {
        this.entityType = clazz;
        clazz.cast(array);
        scan = new Scanner(System.in);
    }

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
