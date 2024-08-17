package aston.org.searching;

import java.util.List;

public interface SearchStrategy<T extends Comparable<T>> {
    int search(List<T> list, T key);
}