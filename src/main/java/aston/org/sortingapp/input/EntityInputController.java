package aston.org.sortingapp.input;

import aston.org.sortingapp.algorithms.TimSortEven;
import aston.org.sortingapp.models.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EntityInputController <T> {

    public final static String[] inputOptions = {
            "1. Пользовательский ввод",
            "2. Чтение из файла",
            "3. Генерация случайных объектов"
    };
    public final static String[] classOptions = {
            "1. Animal",
            "2. Barrel",
            "3. Human"
    };
    protected AbstractInputMethod<T> entityInput;

    public <V> void setField(String prompt, Consumer<V> setter, Class<V> type, V[] randValues) {
        if (entityInput instanceof InputManually) {
            System.out.println(prompt);
        }
        V value = type.cast(entityInput.initField(type, randValues));
        setter.accept(value);
    }

    public T createEntity(Class<T> entityType, AbstractInputMethod<T> entityInput) {
        this.entityInput = entityInput;
        return switch (entityType.getSimpleName()) {
            case "Animal" -> entityType.cast(new AnimalInitializer().get());
            case "Barrel" -> entityType.cast(new BarrelInitializer().get());
            case "Human" -> entityType.cast(new HumanInitializer().get());
            default -> null;
        };
    }

    public static TimSortEven.NumericFieldAccessor<?> createAccessor(int classOption) {
        return switch (classOption) {
            case 1 -> animal -> ((Animal) animal).getSpecies().length();
            case 2 -> barrel -> ((Barrel) barrel).getVolume();
            case 3 -> human -> ((Human) human).getAge();
            default -> null;
        };
    }

    public static Class<?> getOptionType(int option) {
        return switch (option) {
            case 1 -> Animal.class;
            case 2 -> Barrel.class;
            case 3 -> Human.class;
            default -> null;
        };
    }

    public static Object createKey(int option) {
        return switch (option) {
            case 1 -> new EntityInputController<Animal>().createEntity(Animal.class, new InputManually<>(Animal.class));
            case 2 -> new EntityInputController<Barrel>().createEntity(Barrel.class, new InputManually<>(Barrel.class));
            case 3 -> new EntityInputController<Human>().createEntity(Human.class, new InputManually<>(Human.class));
            default -> null;
        };
    }

    private class AnimalInitializer implements Supplier<Animal> {
        private static final String[] Species = {
                "Wolf", "Bear", "Elephant", "Coyote", "Rabbit",
                "Kangaroo", "Penguin", "Horse", "Leopard", "Fox"
        };
        private static final String[] EyeColor = {
                "Blue", "Green", "Brown", "Amber", "Hazel",
                "Gray", "Yellow", "Red", "Orange", "Violet"
        };
        private static final Boolean[] Fur = { true, false };
        @Override
        public Animal get() {
            Animal.Builder builder = new Animal.Builder();
            setField("Введите вид животного", builder::setSpecies, String.class, Species);
            setField("Введите цвет глаз животного", builder::setEyeColor, String.class, EyeColor);
            setField("Введите наличие шерсти", builder::setFur, Boolean.class, Fur);
            return builder.build();
        }
    }

    private class BarrelInitializer implements Supplier<Barrel> {
        private static final Double[] Volume = { 5., 200. };
        private static final String[] StoredMaterial = {
                "Water", "Oil", "Wine", "Beer", "Grain",
                "Salt", "Sugar", "Honey", "Vinegar", "Petrol"
        };
        private static final String[] Material = {
                "Wood", "Steel", "Plastic", "Aluminum", "Iron",
                "Copper", "Fiberglass", "Ceramic", "Titanium"
        };
        @Override
        public Barrel get() {
            Barrel.Builder builder = new Barrel.Builder();
            setField("Введите объем бочки", builder::setVolume, Double.class, Volume);
            setField("Введите хранимый материал", builder::setStoredMaterial, String.class, StoredMaterial);
            setField("Введите материал изготовления", builder::setMaterial, String.class, Material);
            return builder.build();
        }
    }

    private class HumanInitializer implements Supplier<Human> {
        private static final String[] Gender = { "male", "female" };
        private static final Integer[] Age = { 0, 120 };
        private static final String[] Surname = {
                "Ashworth", "Raynott", "Jarsdel", "Southway", "Silsbury",
                "Ravensdale", "Boulstridge", "Clayworth", "Edeson", "Lowsley"
        };
        @Override
        public Human get() {
            Human.Builder builder = new Human.Builder();
            setField("Введите пол", builder::setGender, String.class, Gender);
            setField("Введите возраст", builder::setAge, Integer.class, Age);
            setField("Введите фамилию", builder::setSurname, String.class, Surname);
            return builder.build();
        }
    }


}
