package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.util.function.Consumer;

public class InputByUser<T> extends EntityInput<T> {

    public InputByUser(Class<T> entityType) {
        super(entityType);
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
                System.out.println("Неверное значение. Введите вещественное число.");
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

    protected void animalInit(Animal.Builder builder) {
        readFromConsole("Введите вид животного", String.class, builder::setSpecies);
        readFromConsole("Введите цвет глаз животного", String.class, builder::setEyeColor);
        readFromConsole("Введите наличие шерсти", Boolean.class, builder::setFur);
    }

    protected void barrelInit(Barrel.Builder builder) {
        readFromConsole("Введите объем бочки", Double.class, builder::setVolume);
        readFromConsole("Введите хранимый материал", String.class, builder::setStoredMaterial);
        readFromConsole("Введите материал изготовления", String.class, builder::setMaterial);
    }

    protected void humanInit(Human.Builder builder) {
        readFromConsole("Введите пол", String.class, builder::setGender);
        readFromConsole("Введите возраст", Integer.class, builder::setAge);
        readFromConsole("Введите фамилию", String.class, builder::setSurname);
    }

}
