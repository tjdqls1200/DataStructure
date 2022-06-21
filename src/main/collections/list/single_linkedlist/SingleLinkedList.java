package main.collections.list.single_linkedlist;

import main.collections.inf.List;

import java.util.NoSuchElementException;

public class SingleLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SingleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    private Node<E> search(int index) {
        indexOutOfBoundsRead(index);

        Node<E> x = head;

        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public void addFirst(E value) {
        if (value == null)
            return;
        Node<E> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    private void addLast(E value) {
        Node<E> newNode = new Node<>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {
        indexOutOfBoundsAdd(index);

        if (0 < index && index < size) {
            Node<E> newNode = new Node<>(value);
            Node<E> prevNode = search(index - 1);
            Node<E> nextNode = prevNode.next;

            prevNode.next = newNode;
            newNode.next = nextNode;
            size++;
        }
        else if (index == 0)
            addFirst(value);
        else
            addLast(value);
    }

    public E remove() {
        if (head == null)
            throw new NoSuchElementException();
        E element = head.data;
        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;
        head = nextNode;
        size--;

        if (size == 0)
            tail = null;
        return element;
    }


    @Override
    public E remove(int index) {
        indexOutOfBoundsRead(index);

        if (index == 0) {
            remove();
        }

        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        E element = removeNode.data;

        prevNode.next = removeNode.next;
        if (prevNode.next == null) {
            tail = prevNode;
        }
        removeNode.data = null;
        removeNode.next = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        if (value == null)
            return false;
        int i = searchObject(value);
        if (i == -1)
            return false;
        remove(i);
        return true;
    }

    public int searchObject(Object value) {
        int i;

        Node<E> point = head;
        for (i = 0; i < size; i++) {
            if (value.equals(point.data))
                return i;
            point = point.next;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> node = search(index);
        node.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return (searchObject(value) >= 0);
    }

    @Override
    public int indexOf(Object value) {
        return searchObject(value);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        for (Node<E> point = head; point != null;) {
            Node<E> temp = point.next;
            point.data = null;
            point.next = null;
            point = temp;
        }
        head = tail = null;
        size = 0;
    }

    private void indexOutOfBoundsAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    private void indexOutOfBoundsRead(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
    }
}
