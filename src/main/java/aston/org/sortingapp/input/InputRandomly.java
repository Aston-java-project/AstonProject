package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.lang.reflect.Array;
import java.util.function.Consumer;
import java.util.Random;

public class InputRandomly<T> extends EntityInput<T> {

    private static String[] randomAnimalSpecies = {
            "Wolf", "Bear", "Elephant", "Coyote", "Rabbit",
            "Kangaroo", "Penguin", "Horse", "Leopard", "Fox"
    };
    private static String[] randomAnimalEyeColor = {
            "Blue", "Green", "Brown", "Amber", "Hazel",
            "Gray", "Yellow", "Red", "Orange", "Violet"
    };
    private static String[] randomBarrelStoredMaterial = {
            "Water", "Oil", "Wine", "Beer", "Grain",
            "Salt", "Sugar", "Honey", "Vinegar", "Petrol"
    };
    private static String[] randomBarrelMaterial = {
            "Wood", "Steel", "Plastic", "Aluminum", "Iron",
            "Copper", "Fiberglass", "Ceramic", "Titanium"
    };
    private static String[] randomHumanSurname = {
            "Ashworth", "Raynott", "Jarsdel", "Southway", "Silsbury",
            "Ravensdale", "Boulstridge", "Clayworth", "Edeson", "Lowsley"
    };

    public InputRandomly(Class<T> entityType) {
        super(entityType);
    }

    private Random random;

    public void performInput() {
        System.out.println("Введите длину массива");
        int arrayLength = scan.nextInt();
        random = new Random();
        array = (T[]) Array.newInstance(entityType, arrayLength);

        for(int i=0; i<arrayLength; i++) {
            array[i] = createInstance();
        }
    }

    private <K,V> void assignRandomly(Class<K> type, V[] randomArray, Consumer<K> setter) {
        int randomIndex = (randomArray.length > 1) ? random.nextInt(randomArray.length) : 0;
        K value = type.cast(randomArray[randomIndex]);
        setter.accept(value);
    }

    protected Animal animalEntry() {
        Animal.Builder animalBuilder = new Animal.Builder();
        assignRandomly(String.class, randomAnimalSpecies, animalBuilder::setSpecies);
        assignRandomly(String.class, randomAnimalEyeColor, animalBuilder::setEyeColor);
        assignRandomly(Boolean.class, new Boolean[]{true, false}, animalBuilder::setFur);
        return animalBuilder.build();
    }

    protected Barrel barrelEntry() {
        Barrel.Builder barrelBuilder = new Barrel.Builder();
        assignRandomly(Double.class, new Double[]{ randomBarrelVolume() } , barrelBuilder::setVolume);
        assignRandomly(String.class, randomBarrelStoredMaterial,barrelBuilder::setStoredMaterial);
        assignRandomly(String.class, randomBarrelMaterial, barrelBuilder::setMaterial);
        return barrelBuilder.build();
    }

    protected Human humanEntry() {
        Human.Builder humanBuilder = new Human.Builder();
        assignRandomly(String.class, new String[]{"male", "female"}, humanBuilder::setGender);
        assignRandomly(Integer.class, new Integer[]{ randomHumanAge() }, humanBuilder::setAge);
        assignRandomly(String.class, randomHumanSurname,humanBuilder::setSurname);
        return humanBuilder.build();
    }

    private Double randomBarrelVolume() { return random.nextDouble(5, 200); };
    private Integer randomHumanAge() { return random.nextInt(0, 120); };

}
