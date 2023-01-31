package collections.mutable.caches;

public abstract class RecencyCache<N, V> extends Cache<N, V> {

    RecencyCache(int capacity) {
        super(capacity);
    }

}
