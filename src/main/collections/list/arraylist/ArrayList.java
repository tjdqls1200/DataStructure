package main.collections.list.arraylist;

import main.collections.inf.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private int size;

    Object[] array;

    public ArrayList() {
        array = EMPTY_ARRAY;
        size = 0;
    }

    public ArrayList(int capacity) {
        array = new Object[capacity];
        size = 0;
    }

    private void resize() {
        int arrayCapacity = array.length;

        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }
        arrayCapacity = getResizeCapacity(arrayCapacity);
        array = Arrays.copyOf(array, Math.max(arrayCapacity, DEFAULT_CAPACITY));
    }

    private int getResizeCapacity(int arrayCapacity) {
        int resizeCapacity = arrayCapacity;
        if (size == arrayCapacity) {
            resizeCapacity = arrayCapacity * 2;
        } else if (size < (arrayCapacity / 2)) {
            resizeCapacity = arrayCapacity / 2;
        }
        return resizeCapacity;
    }

    @Override
    public boolean add(E value) {
        if (size == array.length)
            resize();
        array[size++] = value;
        return true;
    }

    @Override
    public void add(int index, E value) {
        indexOutOfBoundsAdd(index);
        pullElement(index);
        add(value);
    }

    private void pullElement(int index) {
        if (size == index)
            return;
        if (size == array.length)
            resize();
        for (int i = size; i > index; i--)
            array[i] = array[i - 1];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        indexOutOfBoundsRead(index);
        E removeElement = (E) array[index];
        size--;
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[size] = null;
        resize();
        return removeElement;
    }

    @Override
    public boolean remove(Object value) {
        int i = indexOf(value);
        if (i == -1)
            return false;
        remove(i);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        indexOutOfBoundsRead(index);
        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        indexOutOfBoundsRead(index);
        array[index] = value;
    }

    // 읽을 때는 index가 size랑 같으면 예외 포함
    private void indexOutOfBoundsRead(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
    }
    private void indexOutOfBoundsAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean contains(Object value) {
        boolean result = false;
        if (indexOf(value) >= 0)
            result = true;
        return result;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value))
                return i;
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
        for (int i = 0; i < size; i++) {
            array[size] = null;
        }
        size = 0;
        resize();
    }
}
