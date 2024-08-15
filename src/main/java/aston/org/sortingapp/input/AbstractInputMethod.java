package aston.org.sortingapp.input;

import java.lang.reflect.Array;
import java.util.Scanner;

public abstract class AbstractInputMethod<T> {

    protected T[] array;
    protected Class<T> entityType;
    protected Scanner scan;

    protected AbstractInputMethod(Class<T> entityType) {
        this.entityType = entityType;
        scan = new Scanner(System.in);
    }

    protected abstract <R> T read(Class<T> type, R[] randValues);

    public void createArray() {
        int arrayLength = getElementsCount();
        array = (T[]) Array.newInstance(entityType, arrayLength);
        for(int i=0; i<arrayLength; i++) {
            array[i] = createInstance();
        }
    }

    protected  int getElementsCount() {
        System.out.println("Введите длину массива");
        while (!scan.hasNextInt()) {
            System.out.println("Неверное значение. Введите целое число.");
            scan.next();
        }
        return scan.nextInt();
    }

    protected T createInstance() {
        return EntityInputController.createEntity(entityType, this);
    }

    public void printArray() {
        if (array == null || array.length == 0) {
            System.out.println("Нет обьектов в массиве");
        } else {
            for (int i = 0; i < array.length; i++) {
                System.out.println("Объект " + i + ": " + array[i]);
            }
        }
    }

    public T[] getArray() {
        return array;
    }

}
