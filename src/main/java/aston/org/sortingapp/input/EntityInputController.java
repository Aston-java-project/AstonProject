package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EntityInputController {

    // values for random input
    private final static String[] AnimalSpecies = {
            "Wolf", "Bear", "Elephant", "Coyote", "Rabbit",
            "Kangaroo", "Penguin", "Horse", "Leopard", "Fox"
    };
    private final static String[] AnimalEyeColor = {
            "Blue", "Green", "Brown", "Amber", "Hazel",
            "Gray", "Yellow", "Red", "Orange", "Violet"
    };
    private final static Boolean[] AnimalFur = { true, false };
    private final static Double[] BarrelVolume = { 5., 200. };
    private final static String[] BarrelStoredMaterial = {
            "Water", "Oil", "Wine", "Beer", "Grain",
            "Salt", "Sugar", "Honey", "Vinegar", "Petrol"
    };
    private final static String[] BarrelMaterial = {
            "Wood", "Steel", "Plastic", "Aluminum", "Iron",
            "Copper", "Fiberglass", "Ceramic", "Titanium"
    };
    private final static String[] HumanGender = { "male","female" };
    private final static Integer[] HumanAge = { 0, 120 };
    private final static String[] HumanSurname = {
            "Ashworth", "Raynott", "Jarsdel", "Southway", "Silsbury",
            "Ravensdale", "Boulstridge", "Clayworth", "Edeson", "Lowsley"
    };

    protected static AbstractInputMethod entityInput;

    public static <T,V> void readEntity(Supplier<V>  v, Class<T> type, Consumer<T> setter, String setterName) {

        if (entityInput instanceof InputManually) {
            // provide prompt when create an instance manually
            String prompt = getPrompt(setterName);
            System.out.println(prompt);
        }

        V value = v.get();
        setter.accept(type.cast(value));
    }

    public static <T> T createEntity(Class<T> entityType, AbstractInputMethod entityInput) {
        EntityInputController.entityInput = entityInput;
        if (entityType == Animal.class) {
            Animal.Builder animalBuilder = new Animal.Builder();
            animalInit(animalBuilder);
            return (T) animalBuilder.build();
        } else if (entityType == Barrel.class) {
            Barrel.Builder barrelBuilder = new Barrel.Builder();
            barrelInit(barrelBuilder);
            return (T) barrelBuilder.build();
        } else if (entityType == Human.class) {
            Human.Builder humanBuider = new Human.Builder();
            humanInit(humanBuider);
            return (T) humanBuider.build();
        } else {
            return null;
        }
    }

    public static void animalInit(Animal.Builder builder) {
        readEntity(() -> entityInput.read(String.class, AnimalSpecies), String.class, builder::setSpecies, "setSpecies");
        readEntity(() -> entityInput.read(String.class, AnimalEyeColor), String.class, builder::setEyeColor, "setEyeColor");
        readEntity(() -> entityInput.read(Boolean.class, AnimalFur), Boolean.class, builder::setFur, "setFur");
    }

    public static void barrelInit(Barrel.Builder builder) {
        readEntity(() -> entityInput.read(Double.class, BarrelVolume), Double.class, builder::setVolume, "setVolume");
        readEntity(() -> entityInput.read(String.class, BarrelStoredMaterial), String.class, builder::setStoredMaterial, "setStoredMaterial");
        readEntity(() -> entityInput.read(String.class, BarrelMaterial), String.class, builder::setMaterial, "setMaterial");
    }

    public static void humanInit(Human.Builder builder) {
        readEntity(() -> entityInput.read(String.class, HumanGender), String.class, builder::setGender, "setGender");
        readEntity(() -> entityInput.read(Integer.class, HumanAge), Integer.class, builder::setAge, "setAge");
        readEntity(() -> entityInput.read(String.class, HumanSurname), String.class, builder::setSurname, "setSurname");
    }

    private static String getPrompt(String setterName) {
        return switch(setterName) {
            // for Animal
            case "setSpecies" -> "Введите вид животного";
            case "setEyeColor" -> "Введите цвет глаз животного";
            case "setFur" -> "Введите наличие шерсти";
            // for Barrel
            case "setVolume" -> "Введите объем бочки";
            case "setStoredMaterial" -> "Введите хранимый материал";
            case "setMaterial" -> "Введите материал изготовления";
            // for Human
            case "setGender" -> "Введите пол";
            case "setAge" -> "Введите возраст";
            case "setSurname" -> "Введите фамилию";
            // for typo
            default -> "?";
        };
    }

}
