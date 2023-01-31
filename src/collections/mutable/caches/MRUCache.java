package collections.mutable.caches;

public abstract class MRUCache<N, V> extends RecencyCache<N, V> {

    // TODO: Write a test for this
    protected boolean has(V value) {
        return false;
    }

    // TODO: Write a test for this
    public V retrieve(N name) {
        return null;
    }

    public MRUCache(int capacity) {
        super(capacity);
    }

}
