package collections.mutable.caches;

public abstract class LRUCache<N, V> extends RecencyCache<N, V> {

    private final Object[] names;
    private final Object[] values;

    private final int capacity;

    private final int lastIndex;

    private int nextUp = 0;

    protected boolean has(V value) {
        return indexOf(value, this.values, this.capacity) > -1;
    }

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

    @SuppressWarnings("unchecked")
    public V retrieve(N name) {
        V value;
        int index = indexOf(name, this.names, this.nextUp);
        if (index > -1) {
            value = (V) this.values[index];
        } else {
            value = this.create(name);
            this.names[this.nextUp] = name;
            this.values[this.nextUp] = value;
            this.nextUp++;
            if (this.nextUp == this.capacity) {
                this.nextUp--;
            }
        }
        return value;
    }

    public LRUCache(int capacity) {
        super(capacity);
        // TODO: Throw exception if size < MINIMUM_CAPACITY
        // TODO: Throw exception if size > MAXIMUM_CAPACITY
        this.capacity = capacity;
        this.names = new Object[this.capacity];
        this.values = new Object[this.capacity];
        this.lastIndex = this.capacity - 1;    }

}
