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
        V value;
        if (this.nextUp > 0 && name.equals(this.names[0])) {
            value = (V) this.values[0];
        } else {
            value = this.create(name);
            this.names[0] = name;
            this.values[0] = value;
            this.nextUp++;
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
