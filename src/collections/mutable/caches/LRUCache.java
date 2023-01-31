package collections.mutable.caches;

public abstract class LRUCache<N, V> extends RecencyCache<N, V> {

    // TODO: Write a test for this
    protected boolean has(V value) {
        return false;
    }

    // TODO: Write a test for this
    public V retrieve(N name) {
        return null;
    }

    public LRUCache(int capacity) {
        super(capacity);
        // TODO: Throw exception if size < MINIMUM_CAPACITY
        // TODO: Throw exception if size > MAXIMUM_CAPACITY
        // TODO: Initialize queue
    }

}
