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

    protected abstract <V> V initField(Class<V> type, V[] randValues);

    public void createArray(EntityInputController<T> ic) {
        int arrayLength = getElementsCount();
        array = (T[]) Array.newInstance(entityType, arrayLength);
        for(int i=0; i<arrayLength; i++) {
            array[i] = ic.createEntity(entityType, this);
        }
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    protected  int getElementsCount() {
        System.out.println("Введите длину массива");
        while (!scan.hasNextInt()) {
            System.out.println("Неверное значение. Введите целое число.");
            scan.next();
        }
        int i = scan.nextInt();
        if (i <= 0) {
            System.out.println("Недопустимое количество элементов! Введите положительное число.");
        }
        return Math.max(i, 0);
    }

    public T[] getArray() {
        return array;
    }

}
