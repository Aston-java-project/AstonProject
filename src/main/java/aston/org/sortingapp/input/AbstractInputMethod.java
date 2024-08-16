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

    protected abstract <R> T initField(Class<T> type, R[] randValues);

    public boolean createArray() {
        int arrayLength = getElementsCount();
        array = (T[]) Array.newInstance(entityType, arrayLength);
        for(int i=0; i<arrayLength; i++) {
            array[i] = initField();
        }
        return true;
    }

    protected  int getElementsCount() {
        System.out.println("Введите длину массива");
        while (!scan.hasNextInt()) {
            System.out.println("Неверное значение. Введите целое число.");
            scan.next();
        }
        int i = scan.nextInt();
        if (i < 0) {
            System.out.println("Недопустимое количество элементов! ");
        }
        return Math.max(i, 0);
    }

    protected T initField() {
        return EntityInputController.createEntity(entityType, this);
    }

    public T[] getArray() {
        return array;
    }

}
