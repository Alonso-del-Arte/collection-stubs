package collections.immutable.lists;

import collections.immutable.ImmutableCollection;

public class ArrayBackedList<E> extends ImmutableCollection<E> {

    // TODO: Write tests for this
    @Override
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public boolean isEmpty() {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public int size() {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    @Override
    public ArrayBackedList<E> add(E element) {
        return this;
    }

}
