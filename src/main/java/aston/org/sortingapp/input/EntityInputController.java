package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.util.function.Consumer;

public class EntityInputController {

    protected static AbstractInputMethod entityInput;

    public static <T,V> void setField(String prompt, Consumer<T> setter, V value) {
        if (entityInput instanceof InputManually) {
            System.out.println(prompt);
        }
        setter.accept((T)value);
    }

    public static <T> T createEntity(Class<T> entityType, AbstractInputMethod entityInput) {
        EntityInputController.entityInput = entityInput;
        if (entityType == Animal.class) {
            Animal.Builder animalBuilder = new Animal.Builder();
            new AnimalInitializer().init(animalBuilder);
            return (T) animalBuilder.build();
        } else if (entityType == Barrel.class) {
            Barrel.Builder barrelBuilder = new Barrel.Builder();
            new BarrelInitializer().init(barrelBuilder);
            return (T) barrelBuilder.build();
        } else if (entityType == Human.class) {
            Human.Builder humanBuilder = new Human.Builder();
            new HumanInitializer().init(humanBuilder);
            return (T) humanBuilder.build();
        } else {
            return null;
        }
    }

    private interface EntityInitialize <T> {
        void init(T builder);
    }

    private static class AnimalInitializer implements EntityInitialize<Animal.Builder> {
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
        public void init(Animal.Builder builder) {
            EntityInputController.setField(
                    "Введите вид животного",
                    builder::setSpecies,
                    entityInput.initField(String.class, Species));
            EntityInputController.setField(
                    "Введите цвет глаз животного",
                    builder::setEyeColor,
                    entityInput.initField(String.class, EyeColor));
            EntityInputController.setField(
                    "Введите наличие шерсти",
                    builder::setFur,
                    entityInput.initField(Boolean.class, Fur));
        }
    }

    private static class BarrelInitializer implements EntityInitialize<Barrel.Builder> {
        private static final Double[] Volume = { 5., 200. };
        private static final String[] StoredMaterial = {
                "Water", "Oil", "Wine", "Beer", "Grain",
                "Salt", "Sugar", "Honey", "Vinegar", "Petrol"
        };
        private static final String[] Material = {
                "Wood", "Steel", "Plastic", "Aluminum", "Iron",
                "Copper", "Fiberglass", "Ceramic", "Titanium"
        };

        public void init(Barrel.Builder builder) {
            EntityInputController.setField(
                    "Введите объем бочки",
                    builder::setVolume,
                    entityInput.initField(Double.class, Volume));
            EntityInputController.setField(
                    "Введите хранимый материал",
                    builder::setStoredMaterial,
                    entityInput.initField(String.class, StoredMaterial));
            EntityInputController.setField(
                    "Введите материал изготовления",
                    builder::setMaterial,
                    entityInput.initField(String.class, Material));
        }
    }

    private static class HumanInitializer implements EntityInitialize<Human.Builder> {
        private static final String[] Gender = { "male", "female" };
        private static final Integer[] Age = { 0, 120 };
        private static final String[] Surname = {
                "Ashworth", "Raynott", "Jarsdel", "Southway", "Silsbury",
                "Ravensdale", "Boulstridge", "Clayworth", "Edeson", "Lowsley"
        };

        public void init(Human.Builder builder) {
            EntityInputController.setField(
                    "Введите пол",
                    builder::setGender,
                    entityInput.initField(String.class, Gender));
            EntityInputController.setField(
                    "Введите возраст",
                    builder::setAge,
                    entityInput.initField(Integer.class, Age));
            EntityInputController.setField(
                    "Введите фамилию",
                    builder::setSurname,
                    entityInput.initField(String.class, Surname));
        }
    }


}
