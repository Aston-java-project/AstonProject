package aston.org.sortingapp.console;

import java.util.Scanner;

public class Main {

    static String selectAction;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        selectAction = "";

        while (!"exit".equals(selectAction)) {
            UIService.provideOptionsList("Выберите действие:", UIService.menuOptions);
            selectAction = scan.next();

            switch (selectAction) {
                case "1" : {
                    UIService.initArray();
                    break;
                }
                case "2" : {
                    UIService.applySort();
                    break;
                }
                case "3" : {
                    // Search object
                    break;
                }
                case "4" : {
                    UIService.saveToFile();
                    break;
                }
                case "5" : {
                    selectAction = "exit";
                    break;
                }
            }
        }
    }

}