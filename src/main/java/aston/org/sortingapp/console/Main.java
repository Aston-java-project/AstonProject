package aston.org.sortingapp.console;

import java.util.Scanner;

public class Main {

    static String selectedAction;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        selectedAction = "";

        while (!"exit".equals(selectedAction)) {
            UIService.provideOptionsList("Выберите действие:", UIService.menuOptions);
            selectedAction = scan.next();

            switch (selectedAction) {
                case "1" : {
                    UIService.initArray();
                    break;
                }
                case "2" : {
                    UIService.applySort();
                    break;
                }
                case "3" : {
                    UIService.binarySearch();
                    break;
                }
                case "4" : {
                    UIService.saveToFile();
                    break;
                }
                case "5" : {
                    UIService.getInputMethod().printArray();
                    break;
                }
                case "9" : {
                    selectedAction = "exit";
                    break;
                }
            }
        }
    }

}