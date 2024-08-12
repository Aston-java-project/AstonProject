package aston.org.sortingapp.console;

import aston.org.sortingapp.algorithms.*;

import java.util.Scanner;

public class Main {

    static String selectAction;

    public static void main(String[] args) {

        /*пользовательский ввод, где можно заполнить массив объектами выбранного класса,
        искать элемент в массиве, или выйти из программы.
         */

        Scanner scan = new Scanner(System.in);
        selectAction = "";

        while (!"exit".equals(selectAction)) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавление элементов");
            System.out.println("2. Сортировка массива");
            System.out.println("3. Поиск элемента");
            System.out.println("4. Сохранение элементов");
            System.out.println("5. Выход из программы");

            selectAction = scan.next();

            switch (selectAction) {
                case "1" : {
                    InputService.start();
                    break;
                }

                case "2" : {
                    System.out.println("Выберите тип сортировки:");
                    System.out.println("1. Нормальная сортировка");
                    System.out.println("2. Сортировка по четным значениям");
                    String selectSort = scan.next();
                    if ("1".equals(selectSort)) InputService.applySortOption(new TimSort());
                    if ("2".equals(selectSort)) InputService.applySortOption(new TimSortEven());
                    break;
                }

                case "3" : {

                    break;
                }

                case "4" : {
                    break;
                }

                case "5" : {
                    selectAction = "exit";
                    break;
                }

                default : {

                }
            }

        }

    }

}