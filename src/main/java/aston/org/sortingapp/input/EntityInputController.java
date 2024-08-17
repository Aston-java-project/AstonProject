package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.util.function.Consumer;

public class EntityInputController {

    protected static AbstractInputMethod entityInput;

    public static <T,V> void setField(String prompt, Consumer<T> setter, Class<T> type, T[] randValues) {
        if (entityInput instanceof InputManually) {
            System.out.println(prompt);
        }
        T value = type.cast(entityInput.initField(type, randValues));
        setter.accept((T)value);
    }

    public static <T> T createEntity(Class<T> entityType, AbstractInputMethod entityInput) {
        EntityInputController.entityInput = entityInput;
        if (entityType == Animal.class) {
            return (T) new AnimalInitializer().init();
        } else if (entityType == Barrel.class) {
            return (T) new BarrelInitializer().init();
        } else if (entityType == Human.class) {
            return (T) new HumanInitializer().init();
        } else {
            return null;
        }
    }

    private interface EntityInitialize <T> {
        T init();
    }

    private static class AnimalInitializer implements EntityInitialize<Animal> {
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
        public Animal init() {
            Animal.Builder builder = new Animal.Builder();
            EntityInputController.setField("Введите вид животного", builder::setSpecies, String.class, Species);
            EntityInputController.setField("Введите цвет глаз животного", builder::setEyeColor, String.class, EyeColor);
            EntityInputController.setField("Введите наличие шерсти", builder::setFur, Boolean.class, Fur);
            return builder.build();
        }
    }

    private static class BarrelInitializer implements EntityInitialize<Barrel> {
        private static final Double[] Volume = { 5., 200. };
        private static final String[] StoredMaterial = {
                "Water", "Oil", "Wine", "Beer", "Grain",
                "Salt", "Sugar", "Honey", "Vinegar", "Petrol"
        };
        private static final String[] Material = {
                "Wood", "Steel", "Plastic", "Aluminum", "Iron",
                "Copper", "Fiberglass", "Ceramic", "Titanium"
        };
        public Barrel init() {
            Barrel.Builder builder = new Barrel.Builder();
            EntityInputController.setField("Введите объем бочки", builder::setVolume, Double.class, Volume);
            EntityInputController.setField("Введите хранимый материал", builder::setStoredMaterial, String.class, StoredMaterial);
            EntityInputController.setField("Введите материал изготовления", builder::setMaterial, String.class, Material);
            return builder.build();
        }
    }

    private static class HumanInitializer implements EntityInitialize<Human> {
        private static final String[] Gender = { "male", "female" };
        private static final Integer[] Age = { 0, 120 };
        private static final String[] Surname = {
                "Ashworth", "Raynott", "Jarsdel", "Southway", "Silsbury",
                "Ravensdale", "Boulstridge", "Clayworth", "Edeson", "Lowsley"
        };
        public Human init() {
            Human.Builder builder = new Human.Builder();
            EntityInputController.setField("Введите пол", builder::setGender, String.class, Gender);
            EntityInputController.setField("Введите возраст", builder::setAge, Integer.class, Age);
            EntityInputController.setField("Введите фамилию", builder::setSurname, String.class, Surname);
            return builder.build();
        }
    }


}
