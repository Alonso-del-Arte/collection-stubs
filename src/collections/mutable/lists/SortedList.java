package collections.mutable.lists;

import collections.IntegerIndexedCollection;
import collections.mutable.ArrayBackedCollection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedList<E extends Comparable<E>> extends ArrayBackedCollection<E>
        implements IntegerIndexedCollection<E>, Iterable<E> {

    private Object[] elements;

    // TODO: Write tests for this
    @Override
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public boolean isEmpty() {
        return true;
    }

    // TODO: Write tests for this
    @Override
    public int size() {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    @Override
    public boolean add(E element) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int indexOf(E element) {
        // TODO: Check element is not null
        int rangeBegin = 0;
        int rangeCenter = this.elements.length / 2;
        int prevCenter = rangeCenter - 1;
        int rangeEnd = this.elements.length;
        while (rangeCenter != prevCenter) {
            int comparison = element.compareTo((E) this.elements[rangeCenter]);
            if (comparison == 0) {
                return rangeCenter;
            }
            if (comparison < 0) {
                rangeEnd = rangeCenter;
            } else {
                rangeBegin = rangeCenter;
            }
            prevCenter = rangeCenter;
            rangeCenter = rangeBegin + ((rangeEnd - rangeCenter) / 2);
        }
        return -1;
    }

    // TODO: Write excessive positive index test
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0) {
            String excMsg = "Index " + index + " is not valid";
            throw new IndexOutOfBoundsException(excMsg);
        }
        if (index >= this.elements.length) {
            return null;
        }
        return (E) this.elements[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < SortedList.this.elements.length;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (this.index == SortedList.this.elements.length) {
                    String elementWord = this.index == 1
                            ? " element" : " elements";
                    String excMsg
                            = "This iterator has already given out all of "
                            + this.index + elementWord;
                    throw new NoSuchElementException(excMsg);
                }
                return (E) SortedList.this.elements[index++];
            }

        };
    }

    public SortedList() {
        //
    }

    SortedList(E[] originalElements) {
        this.elements = new Object[originalElements.length];
        System.arraycopy(originalElements, 0, this.elements, 0,
                originalElements.length);
        Arrays.sort(this.elements);
    }

}
