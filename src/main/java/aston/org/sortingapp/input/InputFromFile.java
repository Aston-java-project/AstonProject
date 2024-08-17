package aston.org.sortingapp.input;

import aston.org.sortingapp.models.*;

import java.io.*;

public class InputFromFile<T> extends AbstractInputMethod<T> {

    public InputFromFile(Class<T> entityType) {
        super(entityType);
    }

    @Override
    public void createArray(EntityInputController<T> ic) {
        String fileName;
        if (entityType == Animal.class) {
            fileName = "Animal.data";
        } else if (entityType == Barrel.class) {
            fileName = "Barrel.data";
        } else if (entityType == Human.class) {
            fileName = "Human.data";
        } else {
            fileName = "Other.data";
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            array = (T[]) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Отсутствует файл с данными!");
        } catch (EOFException e) {
            System.out.println("Неожиданный конец файла!");
        } catch (StreamCorruptedException e) {
            System.out.println("Данные повреждены или не соотвутствуют формату");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }

    @Override
    protected <V> V initField(Class<V> type, V[] randValues) {
        // The array has already been initialized in the 'createArray' method
        return null;
    }

}
