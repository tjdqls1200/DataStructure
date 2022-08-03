package main.collections.list.stack;

import main.collections.inf.StackInterface;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> implements StackInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private Object[] array;
    private int size;

    public Stack() {
        array = EMPTY_ARRAY;
        size = 0;
    }

    public Stack(int capacity) {
        this.array = new Object[capacity];
        size = 0;
    }

    private void resize() {
        int arrayCapacity = array.length;
        if (arrayCapacity == 0) {
            arrayCapacity = DEFAULT_CAPACITY;
        } else if (size == arrayCapacity) {
            arrayCapacity = arrayCapacity * 2;
        } else if (size < (arrayCapacity/2)) {
            arrayCapacity = Math.max((arrayCapacity/2), DEFAULT_CAPACITY);
        } else {
            return;
        }
        array = Arrays.copyOf(array, arrayCapacity);
    }


    @Override
    public E push(E item) {
        if (size == array.length)
            resize();
        array[size] = item;
        size++;
        return item;
    }

    @Override
    public E pop() {
        E item = peak();
        removeItemAt(size - 1);
        return item;
    }

    private void removeItemAt(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = size - index - 1;
        // 중간 Index 일 경우 한칸씩 땡겨서 복사
        if (j > 0) {
            System.arraycopy(array, index + 1, array, index, j);
        }
        array[size - 1] = null;
        size--;
        resize();
    }

    @Override
    public E peak() {
        if (size == 0)
            throw new EmptyStackException();
        return itemAt(size - 1);
    }

    @SuppressWarnings("unchecked")
    public E itemAt(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (E) array[index];
    }

    @Override
    public int search(Object value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean empty() {
        return false;
    }
}
