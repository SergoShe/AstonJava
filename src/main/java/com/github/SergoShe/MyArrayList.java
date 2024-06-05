package com.github.SergoShe;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * A simple implementation of an ArrayList-like data structure.
 *
 * @param <E> the type of elements in this list
 */
public class MyArrayList<E> {

    private Object[] elementData;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param capacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            elementData = new Object[capacity];
        } else if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Capacity must be great than 0");
        }
    }

    /**
     * Constructs a list containing the elements of the specified array.
     *
     * @param newArray the array whose elements are to be placed into this list
     */
    public MyArrayList(E[] newArray) {
        elementData = new Object[newArray.length];
        addAll(newArray);
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param newElem element to be appended to this list
     */
    public void add(E newElem) {
        add(newElem, size);
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param newElem element to be inserted
     * @param index   index at which the specified element is to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size})
     */
    public void add(E newElem, int index) {
        checkIndexForAdd(index);
        if (size == elementData.length) {
            ensureCapacity(size + 1);
        }
        arrayCopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = newElem;
        size++;
    }

    /**
     * Appends all the elements in the specified array to the end of this list.
     *
     * @param newArray array containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     */
    public boolean addAll(E[] newArray) {
        return addAll(newArray, size);
    }

    /**
     * Inserts all the elements in the specified array into this list, starting
     * at the specified position.
     *
     * @param newArray array containing elements to be added to this list
     * @param index    index at which to insert the first element from the specified array
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size})
     */
    public boolean addAll(E[] newArray, int index) {
        checkIndexForAdd(index);
        int numNew = newArray.length;
        if (numNew == 0) {
            return false;
        }
        if (numNew > (elementData.length - size)) {
            ensureCapacity(numNew + size);
        }
        int numMoved = size - index;
        if (numMoved > 0) {
            arrayCopy(elementData, index, elementData, index + numMoved, numMoved);
        }
        arrayCopy(newArray, 0, elementData, index, numNew);
        size += numNew;
        return true;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param elem  element to be stored at the specified position
     * @param index index of the element to replace
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size})
     */
    public void set(E elem, int index) {
        checkIndex(index);
        elementData[index] = elem;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size})
     */
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size})
     * @throws NoSuchElementException    if this list is empty
     */
    public E remove(int index) {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        checkIndex(index);
        E removedElem = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            arrayCopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return removedElem;
    }

    /**
     * Removes all the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        IntStream.range(0, size).forEach(index -> elementData[index] = null);
        size = 0;
    }

    /**
     * Increases the capacity of this {@code MyArrayList} instance, if necessary,
     * to ensure that it can hold at least the number of elements specified by
     * the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = (elementData.length * 3) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            Object[] newElemData = new Object[newCapacity];
            IntStream.range(0, size).forEach(index -> newElemData[index] = elementData[index]);
            elementData = newElemData;
        }
    }

    /**
     * Checks if the given index is in range. If not, throws an appropriate
     * runtime exception. This method does not check if the list is empty.
     *
     * @param index the index to be checked
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size})
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Checks if the given index is in range for adding a new element. If not,
     * throws an appropriate runtime exception.
     *
     * @param index the index to be checked
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size})
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Copies elements from one array to another. This method handles overlapping
     * ranges correctly.
     *
     * @param srcArray  the source array
     * @param srcPos    starting position in the source array
     * @param destArray the destination array
     * @param destPos   starting position in the destination array
     * @param length    the number of array elements to be copied
     * @throws NullPointerException      if the source or destination array is null
     * @throws IndexOutOfBoundsException if the copying would cause access of data
     *                                   outside array bounds
     */
    private void arrayCopy(Object[] srcArray, int srcPos, Object[] destArray, int destPos, int length) {
        if (srcArray == null || destArray == null) {
            throw new NullPointerException("srcArray or destArray is null");
        }
        int srcLength = srcArray.length;
        int destLength = destArray.length;

        if (srcPos < 0 || destPos < 0 || length < 0 || srcPos + length > srcLength || destPos + length > destLength) {
            throw new IndexOutOfBoundsException("Array index out of bounds");
        }

        if (srcArray == destArray && srcPos < destPos && destPos < srcPos + length) {
            for (int i = length - 1; i >= 0; i--) {
                destArray[destPos + i] = srcArray[srcPos + i];
            }
        } else {
            IntStream.range(0, length).forEach(i -> destArray[destPos + i] = srcArray[srcPos + i]);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> arrayList = (MyArrayList<?>) o;
        return size == arrayList.size && Objects.deepEquals(elementData, arrayList.elementData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(elementData), size);
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                '}';
    }
}