package aston.org.sortingapp.console;

public class Main {

    static String selectedAction;

    public static void main(String[] args) {

        selectedAction = "";
        while (!"exit".equals(selectedAction) && !"выход".equals(selectedAction)) {
            UIService.provideOptionsList("\nВыберите действие:", UIService.menuOptions);
            selectedAction = UIService.scan.next().trim().toLowerCase();

            switch (selectedAction) {
                case "1" : {
                    UIService.initArray();
                    break;
                }
                case "2" : {
                    UIService.arraySort();
                    break;
                }
                case "3" : {
                    UIService.searchElement();
                    break;
                }
                case "4" : {
                    UIService.saveToFile();
                    break;
                }
                case "5" : {
                    UIService.printArray();
                    break;
                }
                case "9" : {
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Действие не выбрано");
                    break;
                }
            }
        }
    }

}