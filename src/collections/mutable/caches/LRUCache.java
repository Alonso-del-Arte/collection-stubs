package collections.mutable.caches;

/**
 * Fixed-capacity least recently used (LRU) cache. Inspired by
 * <code>sun.misc.LRUCache</code> as used in <code>java.util.Scanner</code> (JDK
 * 8).
 * @param <N> The name type for the cached values. Preferably a type that is
 *           very easy to recalculate. For example, <code>String</code>.
 * @param <V> The type of the cached values. Preferably a type with values that
 *           are expensive enough to recalculate that caching improves
 *           performance. For example, <code>java.util.regex.Pattern</code>.
 * @author Alonso del Arte
 */
public abstract class LRUCache<N, V> extends RecencyCache<N, V> {

    private final Object[] names;
    private final Object[] values;

    private final int capacity;

    private int nextUp = 0;

    private static int indexOf(Object obj, Object[] array, int endBound) {
        boolean found = false;
        int curr = 0;
        while (!found && curr < endBound) {
            found = obj.equals(array[curr]);
            curr++;
        }
        if (found) {
            return curr - 1;
        } else {
            return -1;
        }
    }

    private static void moveToFront(Object[] objects, int position) {
        Object first = objects[position];
        System.arraycopy(objects, 0, objects, 1, position);
        objects[0] = first;
    }

    protected boolean has(V value) {
        return indexOf(value, this.values, this.capacity) > -1;
    }

    @SuppressWarnings("unchecked")
    public V retrieve(N name) {
        V value;
        int index = indexOf(name, this.names, this.capacity);
        if (index > -1) {
            value = (V) this.values[index];
        } else {
            value = this.create(name);
            this.names[this.nextUp] = name;
            this.values[this.nextUp] = value;
            index = this.nextUp;
            this.nextUp++;
            if (this.nextUp == this.capacity) {
                this.nextUp--;
            }
        }
        if (index > 0) {
            moveToFront(this.names, index);
            moveToFront(this.values, index);
        }
        return value;
    }

    public LRUCache(int capacity) {
        super(capacity);
        this.capacity = capacity;
        this.names = new Object[this.capacity];
        this.values = new Object[this.capacity];
    }

}
