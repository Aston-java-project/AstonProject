package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.util.function.Consumer;
import java.util.Random;

public class InputRandomly<T> extends EntityInput<T> {

    private final static String[] randomAnimalSpecies = {
            "Wolf", "Bear", "Elephant", "Coyote", "Rabbit",
            "Kangaroo", "Penguin", "Horse", "Leopard", "Fox"
    };
    private final static String[] randomAnimalEyeColor = {
            "Blue", "Green", "Brown", "Amber", "Hazel",
            "Gray", "Yellow", "Red", "Orange", "Violet"
    };
    private final static String[] randomBarrelStoredMaterial = {
            "Water", "Oil", "Wine", "Beer", "Grain",
            "Salt", "Sugar", "Honey", "Vinegar", "Petrol"
    };
    private final static String[] randomBarrelMaterial = {
            "Wood", "Steel", "Plastic", "Aluminum", "Iron",
            "Copper", "Fiberglass", "Ceramic", "Titanium"
    };
    private final static String[] randomHumanSurname = {
            "Ashworth", "Raynott", "Jarsdel", "Southway", "Silsbury",
            "Ravensdale", "Boulstridge", "Clayworth", "Edeson", "Lowsley"
    };

    private final Random random;

    public InputRandomly(Class<T> entityType) {
        super(entityType);
        random = new Random();
    }

    private Double randomBarrelVolume() { return .0 + random.nextInt(5, 200); };
    private Integer randomHumanAge() { return random.nextInt(0, 120); };

    private <K,V> void assignRandomly(Class<K> type, V[] randomArray, Consumer<K> setter) {
        int randomIndex = (randomArray.length > 1) ? random.nextInt(randomArray.length) : 0;
        K value = type.cast(randomArray[randomIndex]);
        setter.accept(value);
    }

    protected void animalInit(Animal.Builder builder) {
        assignRandomly(String.class, randomAnimalSpecies, builder::setSpecies);
        assignRandomly(String.class, randomAnimalEyeColor, builder::setEyeColor);
        assignRandomly(Boolean.class, new Boolean[]{true,false}, builder::setFur);
    }

    protected void barrelInit(Barrel.Builder builder) {
        assignRandomly(Double.class, new Double[]{randomBarrelVolume()}, builder::setVolume);
        assignRandomly(String.class, randomBarrelStoredMaterial, builder::setStoredMaterial);
        assignRandomly(String.class, randomBarrelMaterial, builder::setMaterial);
    }

    protected void humanInit(Human.Builder builder) {
        assignRandomly(String.class, new String[]{"male","female"}, builder::setGender);
        assignRandomly(Integer.class, new Integer[]{randomHumanAge()}, builder::setAge);
        assignRandomly(String.class, randomHumanSurname, builder::setSurname);
    }

}
