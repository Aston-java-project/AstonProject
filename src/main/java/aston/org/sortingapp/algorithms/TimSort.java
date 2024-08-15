package aston.org.sortingapp.algorithms;

import java.util.Arrays;
import java.util.Stack;

public class TimSort <T> implements SortStrategy<T>{

    @Override
    public void sort(Comparable<T>[] arr){
        timSort(arr);
    }

    private void timSort(Comparable<T>[] arr){
        int N = arr.length;
        if(N < 2){
            return;
        }
        int minrun = getMinrun(N);
        Stack<Range> stack = new Stack<>();
        int currentIndexArr = 0;
        int startRun = currentIndexArr++;
        int lengtRun = 1;
        while(currentIndexArr < N){
            if(arr[currentIndexArr].compareTo((T)arr[currentIndexArr - 1]) > 0){
                currentIndexArr++;
                lengtRun++;
            }else{
                if(lengtRun < minrun){
                    lengtRun = Math.min(minrun, lengtRun + (N - currentIndexArr));
                }
                insertionSort(arr, startRun, startRun + lengtRun - 1, startRun);
                stack.push(new Range(startRun, lengtRun));
                currentIndexArr = startRun + lengtRun;
                startRun = currentIndexArr;
                lengtRun = 1;
            }
        }
        if(startRun < N) {
            stack.push(new Range(startRun, lengtRun));
            insertionSort(arr, startRun, startRun + lengtRun - 1, startRun);
        }
        while(stack.size() > 1){
            Range firstRun = stack.pop();
            Range sekondRun = stack.pop();
            merge(arr, firstRun.start, firstRun.length, sekondRun.start, sekondRun.length);
            stack.push(new Range(sekondRun.start, firstRun.length + sekondRun.length));
        }
    }

    private static int getMinrun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }

    private void insertionSort(Comparable<T>[] arr, int start, int end, int startSort) {
        for (int i = startSort; i <= end; ++i) {
            T current = (T)arr[i];
            int j = i - 1;
            while ((j >= start) && (arr[j].compareTo(current) > 0)) {
                swap(arr, j, j + 1);
                --j;
            }
        }
    }

    private void swap(Comparable<T>[] arr, int i, int j){
        Comparable<T> temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void merge(Comparable<T>[] arr, int firstStart, int firstLength, int secondStart, int secondLength) {
        if (secondStart < firstStart) {
            int temp = firstStart;
            firstStart = secondStart;
            secondStart = temp;
            temp = firstLength;
            firstLength = secondLength;
            secondLength = temp;

        }
        Comparable<T>[] tempArr = Arrays.copyOfRange(arr, firstStart, firstStart + firstLength);
        int i = 0;
        int j = secondStart;
        int k = firstStart;
        while (i < tempArr.length && j < secondStart + secondLength) {
            System.out.println();
            if (tempArr[i].compareTo((T) arr[j]) < 0) {
                arr[k++] = tempArr[i++];
            } else {
                arr[k++] = arr[j++];
            }
        }
        while (i < tempArr.length){
            arr[k++] = tempArr[i++];
        }
        while (j < secondStart + secondLength ){
            arr[k++] = arr[j++];
        }

    }

    static class Range {
        int start;
        int length;

        @Override
        public String toString() {
            return "Range{" + "start=" + start + ", length=" + length + '}';
        }

        public Range(int start, int length){
            this.start = start;
            this.length = length;

        }
    }
}
