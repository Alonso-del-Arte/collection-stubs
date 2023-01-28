package collections.mutable.sets;

public class ArrayBackedSet<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private int nextUp = 0;

    private Object[] elements;

    public boolean add(E element) {
        this.elements[this.nextUp] = element;
        this.nextUp++;
        return element != null;
    }

    public boolean contains(E element) {
        boolean flag = false;
        int index = 0;
        while (!flag && index < this.nextUp) {
            flag = element.equals(this.elements[index]);
            index++;
        }
        return flag;
    }

    // TODO: Write test for this
    public boolean remove(E element) {
        return true;
    }

    // TODO: Write test for this
    public int size() {
        return -1;
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
