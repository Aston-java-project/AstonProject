package aston.org.sortingapp.console;

import aston.org.sortingapp.algorithms.*;
import aston.org.sortingapp.input.*;
import aston.org.sortingapp.models.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UIService {

    private static final Scanner scan = new Scanner(System.in);
    private static int classOption;
    private static AbstractInputMethod inputMethod;

    static String[] menuOptions = {
            "1. Инициализация массива",
            "2. Сортировка массива",
            "3. Поиск элемента",
            "4. Сохранение в файл",
            "5. Вывод на экран",
            "9. Выход из программы"
    };
    public static String[] inputOptions = {
            "1. Пользовательский ввод",
            "2. Чтение из файла",
            "3. Генерация случайных объектов"
    };
    static String[] classOptions = {
            "1. Animal",
            "2. Barrel",
            "3. Human"
    };
    static String[] sortOptions = {
            "1. Нормальная сортировка",
            "2. Сортировка по четным значениям",
    };

    public static void initArray() {
        provideOptionsList("Выберите способ ввода", inputOptions);
        int inputOption = getSelectedOption();
        if (inputOption < 1 || inputOption > inputOptions.length) {
            return;
        }
        provideOptionsList("Выберите класс", classOptions);
        classOption = getSelectedOption();
        if (classOption < 1 || classOption > classOptions.length) {
            return;
        }

        Class<?> entityType = switch (classOption) {
            case 1 -> Animal.class;
            case 2 -> Barrel.class;
            case 3 -> Human.class;
            default -> null;
        };
        if (entityType != null) {
            switch (inputOption) {
                case 1 : inputMethod = new InputManually<>(entityType); break;
                case 2 : inputMethod = new InputFromFile<>(entityType); break;
                case 3 : inputMethod = new InputRandomly<>(entityType); break;
            }
            inputMethod.createArray(new EntityInputController<>());
            printArray();
        }
    }

    public static void saveToFile() {
        if (inputMethod == null || inputMethod.getArray().length < 1) {
            System.out.println("Нет данных для сохранения!");
            return;
        }
        String fileName = switch (classOption) {
            case 1 -> "Animal.data";
            case 2 -> "Barrel.data";
            case 3 -> "Human.data";
            default -> "Other.data";
        };
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(inputMethod.getArray());
            System.out.println("Данные записаны в файл: " + fileName);
        } catch (Exception e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }
    }

    public static void provideOptionsList(String prompt, String[] options) {
        System.out.println(prompt);
        Arrays.stream(options).forEach(System.out::println);
    }

    public static int getSelectedOption() {
        if (scan.hasNextInt()) {
            return scan.nextInt();
        }
        return -1;
    }

    public static Object[] getArrayOrNull() {
        if (inputMethod == null) {
            System.out.println("Требуется инициализация массива!");
            return null;
        }
        var array = inputMethod.getArray();
        if (array == null || array.length == 0) {
            System.out.println("Нет элементов в массиве!");
            return null;
        }
        return array;
    }

    public static void printArray() {
        var array = getArrayOrNull();
        if (array == null) {
            return;
        }
        for (
                int i = 0; i < array.length; i++) {
            System.out.println("Объект " + i + ": " + array[i]);
        }
    }

    public static void binarySearch() {
        var array = getArrayOrNull();
        if (array == null) {
            return;
        }
        System.out.println("Введиите данные для поиска");
        Object obj = switch (classOption) {
            case 1 -> new EntityInputController<Animal>().createEntity(Animal.class, new InputManually<>(Animal.class));
            case 2 -> new EntityInputController<Barrel>().createEntity(Barrel.class, new InputManually<>(Barrel.class));
            case 3 -> new EntityInputController<Human>().createEntity(Human.class, new InputManually<>(Human.class));
            default -> null;
        };
        if (array[0].getClass().isInstance(obj)) {
            int index = BinarySearch.search(array, obj);
            if (index >= 0) {
                System.out.println("Индекс найденного элемента: " + index);
            } else {
                System.out.println("Элемент не найден");
            }
        }
    }

    public static void arraySort() {
        provideOptionsList("Выберите тип сортировки:", sortOptions);
        int sortOption = getSelectedOption();
        if (sortOption < 1 || sortOption > sortOptions.length) {
            return;
        }
        SortStrategy<Object> strategy = switch(sortOption) {
            case 1 -> new TimSort<>();
            case 2 -> new TimSortEven<>();
            default -> null;
        };
        var array = getArrayOrNull();
        if (array != null && array.length > 0 && strategy != null) {
            strategy.sort((Comparable[]) array);
        }
    }


}


