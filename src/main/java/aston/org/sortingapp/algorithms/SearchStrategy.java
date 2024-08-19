package aston.org.sortingapp.algorithms;

import java.util.List;

public interface SearchStrategy<T extends Comparable<T>> {
    int search(List<T> list, T key);

    int search(T[] array, T key);
}