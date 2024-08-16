package aston.org.sortingapp.models;

import java.io.Serializable;

public class Animal implements Comparable<Animal>, Serializable {
    /*Имплементирует интерфейс Comparable или Comparator.
    Также должен реализовывать паттерн Builder
     */
    private static final long serialVersionUID = 1L;
    private final String species;
    private final String eyeColor;
    private final Boolean fur;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.fur = builder.fur;
    }

    public String getSpecies() {
        return species;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean getFur() {
        return fur;
    }

    @Override
    public int compareTo(Animal other) {
        int speciesComparison = this.species.compareTo(other.species);
        if (speciesComparison != 0) {
            return speciesComparison;
        }
        int eyeColorComparison = this.eyeColor.compareTo(other.eyeColor);
        if (eyeColorComparison != 0) {
            return eyeColorComparison;
        }
        return this.fur.compareTo(other.fur);
    }

    public static class Builder {
        private String species;
        private String eyeColor;
        private boolean fur;

        public Builder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder setFur(boolean fur) {
            this.fur = fur;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }

    @Override
    public String toString() {
        return "Животное{" +
                "вид='" + species + '\'' +
                ", цвет глаз='" + eyeColor + '\'' +
                ", шерсть='" + fur + '\'' +
                '}';
    }
}
