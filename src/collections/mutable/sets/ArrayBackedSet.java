package collections.mutable.sets;

public class ArrayBackedSet<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private int nextUp = 0;

    private Object[] elements;

    private void expandCapacity() {
        int oldCapacity = this.elements.length;
        int greaterCapacity = 3 * oldCapacity / 2;
        Object[] replacementArray = new Object[greaterCapacity];
        System.arraycopy(this.elements, 0, replacementArray, 0, this.nextUp);
        this.elements = replacementArray;
    }

    public boolean add(E element) {
        if (this.contains(element)) {
            return false;
        }
        if (this.nextUp == this.elements.length) {
            this.expandCapacity();
        }
        this.elements[this.nextUp] = element;
        this.nextUp++;
        return element != null;
    }

    private int indexOf(E element) {
        boolean found = false;
        int index = 0;
        while (!found && index < this.nextUp) {
            found = element.equals(this.elements[index]);
            index++;
        }
        if (found) {
            return index - 1;
        } else {
            return -1;
        }
    }

    public boolean contains(E element) {
        return this.indexOf(element) > -1;
    }

    public boolean remove(E element) {
        int index = this.indexOf(element);
        if (index > -1) {
            int lastIndex = --this.nextUp;
            this.elements[index] = this.elements[lastIndex];
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return this.nextUp;
    }

    public boolean isEmpty() {
        return this.nextUp == 0;
    }

    // TODO: Write test for this
    public void clear() {
        //
    }

    public ArrayBackedSet() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedSet(int initialCapacity) {
        if (initialCapacity < 2) {
            String excMsg = "Initial capacity should be at least 2, not "
                    + initialCapacity;
            throw new IllegalArgumentException(excMsg);
        }
        this.elements = new Object[initialCapacity];
    }

}
