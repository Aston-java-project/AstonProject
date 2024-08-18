package aston.org.sortingapp.algorithms;

public class SortingService<T> {

    private SortStrategy<T> sortStrategy;

    public SortingService(SortStrategy<T> sortStrategy){
        this.sortStrategy = sortStrategy;
    }
    public void sort(Comparable<T>[] arr){
        sortStrategy.sort(arr);
    }

    public void setSortStrategy(SortStrategy<T> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public SortStrategy<T> getSortStrategy() {
        return sortStrategy;
    }
}
