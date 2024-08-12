package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.lang.reflect.Array;
import java.util.function.Consumer;

public class InputByUser<T> extends EntityInput<T> {

    public InputByUser(Class<T> entityType) {
        super(entityType);
    }

    @Override
    public void performInput() {

        System.out.println("Введите длину массива");
        int arrayLength = scan.nextInt();
        array = (T[]) Array.newInstance(entityType, arrayLength);

        for(int i=0; i<arrayLength; i++) {
            array[i] = createInstance();
        }
    }

    private <K> void readFromConsole(String prompt, Class<K> type, Consumer<K> setter) {
        System.out.println(prompt);
        K value;

        if (type == String.class) {
            value = type.cast(scan.next());
        } else if (type == Integer.class) {
            while (!scan.hasNextInt()) {
                System.out.println("Неверное значение. Введите целое число.");
                scan.next();
            }
            value = type.cast(scan.nextInt());
        } else if (type == Double.class) {
            while (!scan.hasNextDouble()) {
                System.out.println("Неверное значение. Введите вещетвенное число.");
                scan.next();
            }
            value = type.cast(scan.nextDouble());
        } else if (type == Boolean.class) {
            while (!scan.hasNextBoolean()) {
                System.out.println("Неверное значение. Введите true или false.");
                scan.next();
            }
            value = type.cast(scan.nextBoolean());
        } else {
            value = null;
        }

        if (value != null) {
            setter.accept(value);
        }
    }

    private T createInstance() {
        if (entityType == Animal.class) {
            return entityType.cast(animalEntry());
        } else if (entityType == Barrel.class) {
            return entityType.cast(barrelEntry());
        } else if (entityType == Human.class) {
            return entityType.cast(humanEntry());
        }
        return null;
    }

    private Animal animalEntry() {
        Animal animal = new Animal();
        readFromConsole("Введите вид животного", String.class, animal::setSpecies);
        readFromConsole("Введите цвет глаз животного", String.class, animal::setEyeColor);
        readFromConsole("Введите наличие шерсти", Boolean.class, animal::setFur);
        return animal;
    }

    private Barrel barrelEntry() {
        Barrel barrel = new Barrel();
        readFromConsole("Введите объем бочки", Double.class, barrel::setVolume);
        readFromConsole("Введите хранимый материал", String.class, barrel::setStoredMaterial);
        readFromConsole("Введите материал изготовления", String.class, barrel::setMaterial);
        return barrel;
    }

    private Human humanEntry() {
        Human human = new Human();
        readFromConsole("Введите пол", String.class, human::setGender);
        readFromConsole("Введите возраст", Integer.class, human::setAge);
        readFromConsole("Введите фамилию", String.class, human::setSurname);
        return human;
    }

}
