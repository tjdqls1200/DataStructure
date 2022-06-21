package main.collections.list.double_linkedlist;

import main.collections.inf.List;

import java.util.NoSuchElementException;

public class DLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLinkedList() {
        head = null;
        tail = null;
        size = 0;
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

    public Node<E> search(int index) {
        indexOutOfBoundsSearch(index);

        Node<E> point = head;
        if (index > size/2) {
            point = tail;
            for (int i = size - 1; i > index; i--)
                point = point.next;
            return point;
        }
        for (int i = 0; i < index; i++)
            point = point.next;
        return point;
    }

    private void indexOutOfBoundsSearch(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    private void indexOutOfBoundsAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = head;

        if (head != null)
            head.prev = newNode;
        head = newNode;
        size++;

        if (head.next == null)
            tail = head;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(int index, E value) {
        indexOutOfBoundsAdd(index);
        if (index == 0 || index == size) {
            add(value);
            return;
        }
        Node<E> prevNode = search(index - 1);
        Node<E> nextNode = prevNode.next;
        Node<E> newNode = new Node<>(value);

        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = nextNode;
        nextNode.prev = newNode;
        size++;
    }

    public E remove() {
        if (head == null)
            throw new NoSuchElementException();

        Node<E> headNode = head;
        Node<E> nextNode = head.next;
        E element = headNode.data;

        head.data = null;
        head.next = null;

        if (nextNode != null) {
            nextNode.prev = null;
        }
        head = nextNode;
        size--;

        if (size == 0)
            tail = null;
        return element;
    }

    @Override
    public E remove(int index) {
        indexOutOfBoundsSearch(index);

        if (index == 0) {
            return remove();
        }

        Node<E> removeNode = search(index);
        Node<E> prevNode = removeNode.prev;
        Node<E> nextNode = removeNode.next;

        E element = removeNode.data;
        removeNode.data = null;
        removeNode.prev = null;
        removeNode.next = null;

        if (nextNode == null) {
            tail = prevNode;
        } else {
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }
        size--;
        return element;
    }



    @Override
    public boolean remove(Object value) {
        Node<E> point = head;
        int index;
        for (index = 0; point != null; index++) {
            if (value.equals(point.data)) {
                break;
            }
            point = point.next;
        }
        if (point == null)
            return false;
        if (index == 0) {
            remove();
        } else {
            Node<E> prevNode = point.prev;
            Node<E> nextNode = point.next;

            point.prev = null;
            point.next = null;
            point.data = null;

            if (nextNode == null) {
                tail = prevNode;
            } else {
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
        }
        size--;
        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        search(index).data = value;
    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;
        for (Node<E> point = head; point != null; point = point.next) {
            if (point.data.equals(value))
                return index;
            index++;
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        int index = size;
        for (Node<E> point = tail; point != null; point = point.prev) {
            index--;
            if (point.data.equals(value))
                return index;
        }
        return -1;
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
        Node<E> nextNode;
        for (Node<E> point = head; point != null; point = nextNode) {
            nextNode = point.next;
            point.data = null;
            point.prev = null;
            point.next = null;
        }
        head = tail = null;
        size = 0;
    }
}
