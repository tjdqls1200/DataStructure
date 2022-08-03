package main.collections.inf;

public interface StackInterface<E> {

    E push(E item);

    E pop();
    E peak();

    int search(Object value);

    int size();

    void clear();

    boolean empty();
}
