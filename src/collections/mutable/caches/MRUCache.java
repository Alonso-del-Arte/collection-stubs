package collections.mutable.caches;

public abstract class MRUCache<N, V> extends RecencyCache<N, V> {

    MRUCache(int capacity) {
        super(capacity);
    }

}
