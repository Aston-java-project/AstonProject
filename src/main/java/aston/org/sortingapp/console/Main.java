package aston.org.sortingapp.console;

import aston.org.sortingapp.algorithms.TimSortEven;
import aston.org.sortingapp.models.Barrel;
import aston.org.sortingapp.models.Human;

public class Main {
    public static void main(String[] args) {
        TimSortEven<Human> sorter = new TimSortEven<>(Human::getAge);

        Human[] humans = {
                new Human.Builder().setAge(25).setGender("Male").setSurname("Smith").build(),
                new Human.Builder().setAge(30).setGender("Female").setSurname("Johnson").build(),
                new Human.Builder().setAge(45).setGender("Male").setSurname("Williams").build(),
                new Human.Builder().setAge(23).setGender("Female").setSurname("Brown").build(),
                new Human.Builder().setAge(30).setGender("Female").setSurname("Brow").build(),
                new Human.Builder().setAge(28).setGender("Female").setSurname("Bro").build(),
                new Human.Builder().setAge(26).setGender("Female").setSurname("Br").build(),
                new Human.Builder().setAge(24).setGender("Female").setSurname("B").build(),
                new Human.Builder().setAge(22).setGender("Female").setSurname("Browny").build(),
                new Human.Builder().setAge(23).setGender("Female").setSurname("Browno").build()
        };

        sorter.sort(humans);

        // Output sorted array
        for (Human human : humans) {
            System.out.println(human);
        }

        TimSortEven<Barrel> sorter1 = new TimSortEven<>(Barrel::getVolume);

        Barrel[] barrels = {
                new Barrel.Builder().setVolume(25.5).setStoredMaterial("Water").setMaterial("Steel").build(),
                new Barrel.Builder().setVolume(30.0).setStoredMaterial("Oil").setMaterial("Plastic").build(),
                new Barrel.Builder().setVolume(45.7).setStoredMaterial("Wine").setMaterial("Wood").build(),
                new Barrel.Builder().setVolume(22.0).setStoredMaterial("Beer").setMaterial("Metal").build(),
                new Barrel.Builder().setVolume(22.6).setStoredMaterial("Beer").setMaterial("Metal").build(),
                new Barrel.Builder().setVolume(22.4).setStoredMaterial("Beer").setMaterial("Metal").build()
        };

        sorter1.sort(barrels);

        // Output sorted array
        for (Barrel barrel : barrels) {
            System.out.println(barrel);
        }
    }
}
