package aston.org.sortingapp.input;

public class InputManually<T> extends AbstractInputMethod<T> {

    public InputManually(Class<T> entityType) {
        super(entityType);
    }

    protected <V> V initField(Class<V> type, V[] randValues) {
        V value;
        if (type == String.class) {
            value = type.cast(scan.next().trim());
        } else if (type == Integer.class) {
            while (!scan.hasNextInt()) {
                System.out.println("Неверное значение. Введите целое число");
                scan.next();
            }
            value = type.cast(scan.nextInt());
        } else if (type == Double.class) {
            while (!scan.hasNextDouble()) {
                System.out.println("Неверное значение. Введите вещественное число");
                scan.next();
            }
            value = type.cast(scan.nextDouble());
        } else if (type == Boolean.class) {
            String input;
            while (true) {
                input = scan.next().trim().toLowerCase();
                if (input.equals("true") || input.equals("false")) {
                    value = type.cast(Boolean.parseBoolean(input));
                    break;
                } else if (input.equals("да") || input.equals("yes") || input.equals("y")) {
                    value = type.cast(true);
                    break;
                } else if (input.equals("нет") || input.equals("no") || input.equals("n")) {
                    value = type.cast(false);
                    break;
                } else {
                    System.out.println("Неверное значение. Введите \"да\"/\"нет\", y/n");
                }
            }
        } else {
            value = null;
        }

        return value;
    }

}
