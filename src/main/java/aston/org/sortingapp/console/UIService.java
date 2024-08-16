package aston.org.sortingapp.console;

import aston.org.sortingapp.algorithms.*;
import aston.org.sortingapp.input.*;
import aston.org.sortingapp.models.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UIService {

    private static Scanner scan = new Scanner(System.in);
    private static int inputOption;
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

    public static boolean initArray() {

        provideOptionsList("Выберите способ ввода", inputOptions);
        inputOption = returnSelectedOption();
        if (inputOption < 1 || inputOption > inputOptions.length) {
            return false;
        }

        provideOptionsList("Выберите класс", classOptions);
        classOption = returnSelectedOption();
        if (classOption < 1 || classOption > classOptions.length) {
            return false;
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
            inputMethod.createArray();
            printArray();
        }

        return true;
    }

    public static boolean saveToFile() {
        if (inputMethod == null || inputMethod.getArray().length < 1) {
            System.out.println("Нет данных для сохранения!");
            return false;
        }
        String fileName = switch (classOption) {
            case 1 -> "Animal.data";
            case 2 -> "Barrel.data";
            case 3 -> "Human.data";
            default -> "Other.data";
        };

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(inputMethod.getArray());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Данные записаны в файл: " + fileName);
        return true;
    }

    public static void provideOptionsList(String prompt, String[] options) {
        System.out.println(prompt);
        Arrays.stream(options).forEach(System.out::println);
    }

    public static int returnSelectedOption() {
        if (scan.hasNextInt()) {
            return scan.nextInt();
        }
        return -1;
    }

    public static AbstractInputMethod getInputMethod() {
        return inputMethod;
    }

    public static <T> T[] getArrayOrNull() {
        if (inputMethod == null) {
            System.out.println("Требуется инициализация массива!");
            return null;
        }
        var array = inputMethod.getArray();
        if (array == null || array.length == 0) {
            System.out.println("Нет элементов в массиве!");
            return null;
        }
        return (T[]) array;
    }

    public static boolean printArray() {
        var array = getArrayOrNull();
        if (array == null) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println("Объект " + i + ": " + array[i]);
        }
        return true;
    }

    public static boolean binarySearch() {
        var array = getArrayOrNull();
        if (array == null) {
            return false;
        }
        System.out.println("Введиите данные для поиска");
        Object obj = switch (classOption) {
            case 1 -> EntityInputController.createEntity(Animal.class, new InputManually<>(Animal.class));
            case 2 -> EntityInputController.createEntity(Barrel.class, new InputManually<>(Barrel.class));
            case 3 -> EntityInputController.createEntity(Human.class, new InputManually<>(Human.class));
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

        return true;
    }

    public static void applySortOption(SortStrategy strategy) {
        Comparable[] array = getArrayOrNull();
        if (array != null && array.length > 0) {
            strategy.sort(array);
        }
    }

    public static void applySort() {
        provideOptionsList("Выберите тип сортировки:", sortOptions);
        int sortOption = returnSelectedOption();
        if (sortOption < 1 || sortOption > sortOptions.length) {
            return;
        }
        if (sortOption == 1) applySortOption(new TimSort());
        if (sortOption == 2) applySortOption(new TimSortEven());
    }


}


