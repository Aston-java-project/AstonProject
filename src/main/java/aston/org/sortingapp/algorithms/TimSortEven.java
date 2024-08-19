package aston.org.sortingapp.algorithms;

public class TimSortEven<T> implements SortStrategy<T> {
    private final NumericFieldAccessor<T> fieldAccessor;

    // Конструктор класса
    public TimSortEven(NumericFieldAccessor<T> fieldAccessor) {
        this.fieldAccessor = fieldAccessor;
    }

    // Переопределение функции sort
    @Override
    public void sort(Comparable<T>[] arr) {
        sortEvenElements(arr);
    }

    // Функция сортировки
    private void sortEvenElements(Comparable<T>[] arr) {
        int N = arr.length;
        // Проверка на возможность сортировки
        if (N < 2) {
            return;
        }

        // Получение индексов элементов с четными значениями полей (работает в т.ч. для вещ.чисел)
        int[] indices = new int[N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            double value = fieldAccessor.getNumericValue((T) arr[i]);
            int integerPart = (int) value; 
            double fractionalPart = value - integerPart; 
            if (fractionalPart == 0 && integerPart % 2 == 0) {
                indices[count++] = i;
            }
        }

        // Извлечение элементов с четными значениями
        Comparable<T>[] evenValues = (Comparable<T>[]) new Comparable[count];
        for (int i = 0; i < count; i++) {
            evenValues[i] = arr[indices[i]];
        }

        // Сортировка элементов с четным значением с помощью TimSort
        TimSort<T> timSort = new TimSort<>();
        timSort.sort(evenValues);

        // Возврат отсортированных элементов с четным значением поля в их первоначальные позиции
        for (int i = 0; i < count; i++) {
            arr[indices[i]] = evenValues[i];
        }
    }

    // Определение функционального интерфейса для доступа к числовому полю класса
    public interface NumericFieldAccessor<T> {
        double getNumericValue(T obj);
    }
}
