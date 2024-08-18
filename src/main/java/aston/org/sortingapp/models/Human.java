package aston.org.sortingapp.models;

import java.io.Serializable;

public class Human implements Comparable<Human>, Serializable {
    /*Имплементирует интерфейс Comparable или Comparator.
    Также должен реализовывать паттерн Builder
     */
    private static final long serialVersionUID = 1L;
    private final String gender;
    private final int age;
    private final String surname;

    private Human(Builder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.surname = builder.surname;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int compareTo(Human other) {
        return this.surname.compareTo(other.surname);
    }

    public static class Builder {
        private String gender;
        private int age;
        private String surname;

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }

    @Override
    public String toString() {
        return "Человек{" +
                "пол='" + gender + '\'' +
                ", возраст=" + age +
                ", фамилия='" + surname + '\'' +
                '}';
    }
}