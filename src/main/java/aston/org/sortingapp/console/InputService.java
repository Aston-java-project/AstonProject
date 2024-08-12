package aston.org.sortingapp.console;

import aston.org.sortingapp.algorithms.SortStrategy;
import aston.org.sortingapp.input.*;
import aston.org.sortingapp.models.*;
import java.util.Arrays;
import java.util.Scanner;

public class InputService {

    private static Scanner scan = new Scanner(System.in);
    private static int inputOption;
    private static int classOption;
    private static EntityInput entityInput;

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
    static String[] stages = {
            "SelectOption",
            "SelectClass",
            "Input",
            "Sort",
            "Search"
    };

    public static void start() {
        selectClassOption();
        selectInputOption();

        Class<?> entityType = switch (classOption) {
            case 1 -> Animal.class;
            case 2 -> Barrel.class;
            case 3 -> Human.class;
            default -> null;
        };

        if (entityType != null) {
            switch (inputOption) {
                case 1 : entityInput = new InputByUser(entityType); break;
                case 2 : entityInput = new InputFromFile(entityType); break;
                case 3 : entityInput = new InputRandomly(entityType); break;
            }
            entityInput.performInput();
            entityInput.printArray();
        }
    }

    public static void selectInputOption() {
        System.out.println("Выберите вариант заполнения массива:");
        Arrays.stream(inputOptions).forEach(System.out::println);
        scan.hasNextInt();
        inputOption = -1;
        while (inputOption < 1 || inputOption > inputOptions.length) {
            inputOption = scan.nextInt();
        }

    }

    public static void selectClassOption() {
        System.out.println("Выберите класс для заполнения массива:");
        Arrays.stream(classOptions).forEach(System.out::println);
        classOption = -1;
        while (classOption < 1 || classOption > classOptions.length) {
            classOption = scan.nextInt();
        }
    }

    public static void applySortOption(SortStrategy strategy) {
        var array = entityInput.getArray();
        if (array != null && array.length > 1) {
            strategy.sort(array);
        } else {
            System.out.println("Недостаточно элементов для сортировки!");
        }
    }

}
