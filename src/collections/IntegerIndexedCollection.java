package collections;

/**
 * A collection with integer indices that may be queried outside the
 * collection's inheritance hierarchy.
 * @param <E> The type parameter. For example, <code>String</code>.
 * @author Alonso del Arte
 */
public interface IntegerIndexedCollection<E> {

    /**
     * Gives the integer index of the specified element in the collection, if it
     * is present.
     * @param element The element to search for in the collection. For example,
     *                the planet Jupiter in a list of the planets of our solar
     *                system.
     * @return Zero or a positive integer if the element is present in the
     * collection, according to its index in the collection, or if the element
     * is not in the collection, a negative integer (most likely &minus;1, but
     * this is not guaranteed). For example, in a list of the planets of our
     * solar system sorted by distance from the sun at their respective
     * perihelia, Mercury would probably be at index 0, Venus at index 1, etc.
     * Proxima Centauri b would probably give &minus;1.
     * @throws NullPointerException If <code>element</code> is null (assuming
     * the collection does not allow null elements).
     */
    int indexOf(E element);

    /**
     * Retrieves the element at the specified index.
     * @param index The index for the desired element. For example, 12.
     * @return The element at <code>index</code>. For example, in a list of the
     * uppercase letters of the English alphabet sorted from 'A' to 'Z',
     * <code>index</code> 12 would return 'M'.
     * @throws IndexOutOfBoundsException If <code>index</code> is negative, or
     * equal to or greater than the size of the collection.
     */
    E get(int index);

}
