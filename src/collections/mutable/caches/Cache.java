package collections.mutable.caches;

// TODO: Figure out how to pass retention policy up constructor hierarchy
public abstract class Cache<N, V> {

    public static final int MINIMUM_CAPACITY = 4;

    public static final int MAXIMUM_CAPACITY = 128;

    /**
     * Creates a value for a given name. Ideally this function
     * should only be called by {@link #retrieve(java.lang.Object)
     * retrieve()}.
     * @param name The name to create a value for. Once the value is
     * in the cache, this name can be used to retrieve it.
     * @return A new value. Preferably not null.
     */
    protected abstract V create(N name);

    // TODO: Make concrete, write tests
    protected abstract boolean has(V value);

    // TODO: Make concrete, write tests
    public abstract V retrieve(N name);

    Cache(int capacity) {
        if (capacity < MINIMUM_CAPACITY || capacity > MAXIMUM_CAPACITY) {
            String excMsg = "Specified capacity " + capacity
                    + " is outside range " + MINIMUM_CAPACITY + " to "
                    + MAXIMUM_CAPACITY;
            throw new IllegalArgumentException(excMsg);
        }
    }

}
