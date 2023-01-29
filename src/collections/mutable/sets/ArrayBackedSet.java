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

    public boolean contains(E element) {
        boolean found = false;
        int index = 0;
        while (!found && index < this.nextUp) {
            found = element.equals(this.elements[index]);
            index++;
        }
        return found;
    }

    public boolean remove(E element) {
        return false;
    }

    public int size() {
        return this.nextUp;
    }

    // TODO: Write test for this
    public boolean isEmpty() {
        return false;
    }

    // TODO: Write test for this
    public void clear() {
        //
    }

    public ArrayBackedSet() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedSet(int initialCapacity) {
        // TODO: Check initialCapacity > 0
        this.elements = new Object[initialCapacity];
    }

}
