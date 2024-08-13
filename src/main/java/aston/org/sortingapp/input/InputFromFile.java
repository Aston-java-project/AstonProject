package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;
import java.lang.reflect.Array;

public class InputFromFile<T> extends EntityInput<T> {

    public InputFromFile(Class<T> entityType) {
        super(entityType);
    }

    public void performInput() {

    }

    protected Animal animalEntry() {
        Animal.Builder animalBuilder = new Animal.Builder();

        return animalBuilder.build();
    }

    protected Barrel barrelEntry() {
        Barrel.Builder barrelBuilder = new Barrel.Builder();

        return barrelBuilder.build();
    }

    protected Human humanEntry() {
        Human.Builder humanBuilder = new Human.Builder();

        return humanBuilder.build();
    }

}
