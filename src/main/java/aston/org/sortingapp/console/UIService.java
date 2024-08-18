package aston.org.sortingapp.console;

import aston.org.sortingapp.algorithms.*;
import aston.org.sortingapp.input.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UIService {

    protected static final Scanner scan = new Scanner(System.in);
    private static int classOption;
    private static AbstractInputMethod<?> inputMethod;

    static String[] menuOptions = {
            "1. Инициализация массива",
            "2. Сортировка массива",
            "3. Поиск элемента",
            "4. Сохранение в файл",
            "5. Вывод на экран",
            "9. Выход из программы"
    };
    static String[] sortOptions = {
            "1. Нормальная сортировка",
            "2. Сортировка по четным значениям",
    };
    static String[] searchOptions = {
            "1. Двоичный поиск"
    };

    public static void initArray() {
        provideOptionsList("Выберите способ ввода", EntityInputController.inputOptions);
        int inputOption = getSelectedOption(EntityInputController.inputOptions.length);
        if (inputOption <= 0) {
            return;
        }
        provideOptionsList("Выберите класс", EntityInputController.classOptions);
        classOption = getSelectedOption(EntityInputController.classOptions.length);
        if (classOption <= 0) {
            return;
        }
        Class<?> entityType = EntityInputController.getOptionType(classOption);
        if (entityType != null) {
            switch (inputOption) {
                case 1 : inputMethod = new InputManually<>(entityType); break;
                case 2 : inputMethod = new InputFromFile<>(entityType); break;
                case 3 : inputMethod = new InputRandomly<>(entityType); break;
            }
            inputMethod.createArray(new EntityInputController<>());
            System.out.println("Создан массив размера " + inputMethod.getArray().length + ". Тип элементов: " + entityType.getSimpleName());
        }
    }

    public static void saveToFile() {
        if (inputMethod == null || inputMethod.getArray().length < 1) {
            System.out.println("Нет данных для сохранения!");
            return;
        }
        String fileName = inputMethod.getEntityType().getSimpleName() + ".data";

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

    public static int getSelectedOption(int max) {
        int n;
        if (scan.hasNextInt()) {
            n = scan.nextInt();
            if (n <= max && n > 0) {
                return n;
            }
            System.out.printf("Указанная опция отсутствует (%s)\n", n);
        } else {
            System.out.printf("Указанная опция отсутствует (%s)\n", scan.next());
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
        for (int i = 0; i < array.length; i++) {
            System.out.println("Объект " + i + ": " + array[i]);
        }
    }

    public static void searchElement() {
        var array = getArrayOrNull();
        if (array == null) {
            return;
        }
        provideOptionsList("Выберите тип поиска:", searchOptions);
        int searchOption = getSelectedOption(searchOptions.length);
        if (searchOption < 0) {
            return;
        }
        System.out.println("Введите данные для поиска");
        var obj = EntityInputController.createKey(classOption);
        SearchStrategy strategy = switch(searchOption) {
            case 1 -> new BinarySearch<>();
            default -> null;
        };
        int index = strategy.search((Comparable[]) array, (Comparable) obj);
        if (index >= 0) {
            System.out.println("Индекс найденного элемента: " + index);
        } else {
            System.out.println("Элемент не найден");
        }
    }

    public static void arraySort() {
        provideOptionsList("Выберите тип сортировки:", sortOptions);
        int sortOption = getSelectedOption(sortOptions.length);
        if (sortOption > 0) {
            SortStrategy<?> strategy = switch (sortOption) {
                case 1 -> new TimSort<>();
                case 2 -> new TimSortEven<>(EntityInputController.createAccessor(classOption));
                default -> null;
            };
            var array = getArrayOrNull();
            if (array != null && array.length > 0 && strategy != null) {
                strategy.sort((Comparable[])array);
            }
        }
    }


}


