package aston.org.sortingapp.models;

import java.io.Serializable;

public class Barrel implements Comparable<Barrel>, Serializable {
    /*Имплементирует интерфейс Comparable или Comparator.
    Также должен реализовывать паттерн Builder
     */
    private static final long serialVersionUID = 1L;
    private final double volume;
    private final String storedMaterial;
    private final String material;

    private Barrel(Builder builder) {
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;
        this.material = builder.material;
    }

    public double getVolume() {
        return volume;
    }

    public String getStoredMaterial() {
        return storedMaterial;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public int compareTo(Barrel other) {
        return Double.compare(this.volume, other.volume);
    }

    public static class Builder {
        private double volume;
        private String storedMaterial;
        private String material;

        public Builder setVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public Builder setStoredMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            return new Barrel(this);
        }
    }

    @Override
    public String toString() {
        return "Бочка{" +
                "объем=" + volume +
                ", хранимый материал='" + storedMaterial + '\'' +
                ", материал из которого изготовлена='" + material + '\'' +
                '}';
    }
}