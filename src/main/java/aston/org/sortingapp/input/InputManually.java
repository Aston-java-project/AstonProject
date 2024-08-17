package aston.org.sortingapp.input;

public class InputManually<T> extends AbstractInputMethod<T> {

    public InputManually(Class<T> entityType) {
        super(entityType);
    }

    protected T initField(Class<T> type, T[] randValues) {
        T value;
        if (type == String.class) {
            value = type.cast(scan.next());
        } else if (type == Integer.class) {
            while (!scan.hasNextInt()) {
                System.out.println("Неверное значение. Введите целое число.");
                scan.next();
            }
            value = type.cast(scan.nextInt());
        } else if (type == Double.class) {
            while (!scan.hasNextDouble()) {
                System.out.println("Неверное значение. Введите вещественное число.");
                scan.next();
            }
            value = type.cast(scan.nextDouble());
        } else if (type == Boolean.class) {
            while (!scan.hasNextBoolean()) {
                System.out.println("Неверное значение. Введите true или false.");
                scan.next();
            }
            value = type.cast(scan.nextBoolean());
        } else {
            value = null;
        }

        return value;
    }

}
