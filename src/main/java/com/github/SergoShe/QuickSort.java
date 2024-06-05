package com.github.SergoShe;

import java.util.Comparator;

/**
 * This class provides static methods to perform the QuickSort algorithm on a {@link MyArrayList}.
 * It supports sorting using natural ordering or a custom {@link Comparator}.
 */
public class QuickSort {

    /**
     * Sorts the specified {@link MyArrayList} using the QuickSort algorithm with natural ordering.
     *
     * @param <T> the type of elements in the list, which must implement {@link Comparable}
     * @param myArrayList the list to be sorted
     */
    public static <T extends Comparable<? super T>> void quickSort(MyArrayList<T> myArrayList) {
        quickSort(myArrayList, 0, myArrayList.size() - 1, Comparator.naturalOrder());
    }

    /**
     * Sorts the specified {@link MyArrayList} using the QuickSort algorithm with the provided {@link Comparator}.
     *
     * @param <T> the type of elements in the list
     * @param myArrayList the list to be sorted
     * @param comparator the comparator to determine the order of the list
     */
    public static <T> void quickSort(MyArrayList<T> myArrayList, Comparator<? super T> comparator) {
        quickSort(myArrayList, 0, myArrayList.size() - 1, comparator);
    }

    /**
     * Sorts the specified range of the {@link MyArrayList} using the QuickSort algorithm with the provided {@link Comparator}.
     *
     * @param <T> the type of elements in the list
     * @param myArrayList the list to be sorted
     * @param startIndex the starting index of the range to be sorted, inclusive
     * @param endIndex the ending index of the range to be sorted, inclusive
     * @param comparator the comparator to determine the order of the list
     */
    public static <T> void quickSort(MyArrayList<T> myArrayList, int startIndex, int endIndex, Comparator<? super T> comparator) {
        if (startIndex >= endIndex)
            return;

        int lowPos = startIndex;
        int highPos = endIndex;
        T pivot = myArrayList.get((startIndex+endIndex)/2);
        do {
            while (comparator.compare(myArrayList.get(lowPos),pivot) < 0){
                lowPos++;
            }
            while (comparator.compare(myArrayList.get(highPos),pivot) > 0){
                highPos--;
            }
            if (lowPos <= highPos) {
                if (lowPos < highPos)
                    swap(myArrayList, lowPos, highPos);
                lowPos++;
                highPos--;
            }
        }  while (lowPos <= highPos);
        quickSort(myArrayList,startIndex,highPos,comparator);
        quickSort(myArrayList, lowPos, endIndex,comparator);
    }

    /**
     * Swaps the elements at the specified positions in the specified {@link MyArrayList}.
     *
     * @param <T> the type of elements in the list
     * @param myArrayList the list in which to swap elements
     * @param startIndex the index of one element to be swapped
     * @param endIndex the index of the other element to be swapped
     */
    private static <T> void swap(MyArrayList<T> myArrayList, int startIndex, int endIndex) {
        T temp = myArrayList.get(startIndex);
        myArrayList.set(myArrayList.get(endIndex),startIndex);
        myArrayList.set(temp,endIndex);
    }
}