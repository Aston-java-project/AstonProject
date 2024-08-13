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

    protected Animal animalEntry() {
        Animal.Builder animalBuilder = new Animal.Builder();
        readFromConsole("Введите вид животного", String.class, animalBuilder::setSpecies);
        readFromConsole("Введите цвет глаз животного", String.class, animalBuilder::setEyeColor);
        readFromConsole("Введите наличие шерсти", Boolean.class, animalBuilder::setFur);
        return animalBuilder.build();
    }

    protected Barrel barrelEntry() {
        Barrel.Builder barrelBuilder = new Barrel.Builder();
        readFromConsole("Введите объем бочки", Double.class, barrelBuilder::setVolume);
        readFromConsole("Введите хранимый материал", String.class, barrelBuilder::setStoredMaterial);
        readFromConsole("Введите материал изготовления", String.class, barrelBuilder::setMaterial);
        return barrelBuilder.build();
    }

    protected Human humanEntry() {
        Human.Builder humanBuilder = new Human.Builder();
        readFromConsole("Введите пол", String.class, humanBuilder::setGender);
        readFromConsole("Введите возраст", Integer.class, humanBuilder::setAge);
        readFromConsole("Введите фамилию", String.class, humanBuilder::setSurname);
        return humanBuilder.build();
    }

}
