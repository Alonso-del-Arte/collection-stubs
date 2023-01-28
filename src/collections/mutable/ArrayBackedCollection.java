package collections.mutable;

public abstract class ArrayBackedCollection<E> extends MutableCollection<E> {

    protected int indexOf(E element) {
        return Integer.MIN_VALUE;
    }

}
