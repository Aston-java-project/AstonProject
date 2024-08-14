package aston.org.sortingapp.input;

import aston.org.sortingapp.models.Animal;
import aston.org.sortingapp.models.Barrel;
import aston.org.sortingapp.models.Human;
import java.lang.reflect.Array;
import java.util.Scanner;

public abstract class EntityInput<T> {

    protected T[] array;
    protected Class<T> entityType;
    protected Scanner scan;

    protected EntityInput(Class<T> entityType) {
        this.entityType = entityType;
        scan = new Scanner(System.in);
    }

    protected void animalInit(Animal.Builder builder) {}
    protected void barrelInit(Barrel.Builder builder) {}
    protected void humanInit(Human.Builder builder) {}

    protected Animal animalEntry() {
        Animal.Builder animalBuilder = new Animal.Builder();
        animalInit(animalBuilder);
        return animalBuilder.build();
    }

    protected Barrel barrelEntry() {
        Barrel.Builder barrelBuilder = new Barrel.Builder();
        barrelInit(barrelBuilder);
        return barrelBuilder.build();
    }

    protected Human humanEntry() {
        Human.Builder humanBuilder = new Human.Builder();
        humanInit(humanBuilder);
        return humanBuilder.build();
    }

    public void performInput() {
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
        if (entityType == Animal.class) {
            return entityType.cast(animalEntry());
        } else if (entityType == Barrel.class) {
            return entityType.cast(barrelEntry());
        } else if (entityType == Human.class) {
            return entityType.cast(humanEntry());
        }
        return null;
    }

    public void printArray() {
        for (int i=0; i<array.length; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public T[] getArray() {
        return array;
    }

}
