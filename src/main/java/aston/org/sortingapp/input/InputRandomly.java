package aston.org.sortingapp.input;

import java.util.Random;

public class InputRandomly<T> extends AbstractInputMethod<T> {

    private final Random random;

    public InputRandomly(Class<T> entityType) {
        super(entityType);
        random = new Random();
    }

    private Integer randomInteger(int a, int b) { return random.nextInt(a, b); };
    private Double randomDouble(double a, double b) {
        double value = random.nextDouble(a, b);
        return value - value % 0.1;
    };

    @Override
    protected <R> T read(Class<T> type, R[] randValues) {
        T value;
        int randomIndex = (randValues.length > 1) ? random.nextInt(randValues.length) : 0;

        if (type == String.class) {
            value = type.cast(randValues[randomIndex]);
        } else if (type == Double.class) {
            value = type.cast(randomDouble((double)randValues[0], (double)randValues[1]));
        } else if (type == Integer.class) {
            value = type.cast(randomInteger((int)randValues[0], (int)randValues[1]));
        } else if (type == Boolean.class) {
            value = type.cast(randValues[randomIndex]);
        } else {
            value = null;
        }

        return value;
    }

}
