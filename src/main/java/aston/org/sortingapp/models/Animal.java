package aston.org.sortingapp.models;

public class Animal implements Comparable<Animal> {
    /*Имплементирует интерфейс Comparable или Comparator.
    Также должен реализовывать паттерн Builder
     */
    private final String species;
    private final String eyeColor;
    private final String fur;

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

    public String getFur() {
        return fur;
    }

    @Override
    public int compareTo(Animal other) {
        return this.species.compareTo(other.species);
    }

    public static class Builder {
        private String species;
        private String eyeColor;
        private String fur;

        public Builder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder setFur(String fur) {
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
