package main.collections.list.double_linkedlist;

public class Node<E> {
    E data;
    Node<E> prev;
    Node<E> next;

    public Node(E data) {
        this.data = data;
        prev = null;
        next = null;

    }
}
