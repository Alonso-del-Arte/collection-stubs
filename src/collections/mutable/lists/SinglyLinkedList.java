package collections.mutable.lists;

public class SinglyLinkedList<E> extends LinkedList<E> {

    // TODO: Write tests for this
    @Override
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public boolean isEmpty() {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public int size() {
        return 0;
    }

    // TODO: Write tests for this
    @Override
    public boolean add(E element) {
        return false;
    }

    private class Node<E> {

        private final E element;

        Node<E> next; // or should this be previous?

        Node(E elem) {
            this.element = elem;
        }
    }

}
