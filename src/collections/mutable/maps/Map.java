package collections.mutable.maps;

// TODO: Look at Scala maps to figure out how to put these in the collections
//  hierarchy
public abstract class Map<K, V> {

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract void clear();

    public abstract boolean containsKey(K key);

}
