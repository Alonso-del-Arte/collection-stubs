package collections.mutable.caches;

public abstract class LRUCache<N, V> extends RecencyCache<N, V> {

    private final Object[] names;
    private final Object[] values;

    private final int capacity;

    private final int lastIndex;

    private int nextUp = 0;

    // TODO: Write a test for this
    protected boolean has(V value) {
        return false;
    }

    @SuppressWarnings("unchecked")
    public V retrieve(N name) {
        Object currName;
        V value;
        boolean notFound = true;
        int index = 0;
        while (notFound && index < this.nextUp) {
            currName = this.names[index];
            if (currName.equals(name)) {
                notFound = false;
            }
            index++;
        }
        if (!notFound) {
            value = (V) this.values[index - 1];
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
