package collections.immutable;

import collections.Collection;

public abstract class ImmutableCollection<E> extends Collection<E> {

    public abstract ImmutableCollection<E> add(E element);

}
