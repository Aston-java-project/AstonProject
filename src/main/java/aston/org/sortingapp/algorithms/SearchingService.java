package aston.org.sortingapp.algorithms;

import java.util.List;

public class SearchingService<T extends Comparable<T>> {
    private SearchStrategy<T> searchStrategy;

    public void setSearchStrategy(SearchStrategy<T> searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public int search(List<T> list, T key) {
        return searchStrategy.search(list, key);
    }
}