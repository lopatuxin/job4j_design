package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size = 0;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void grow() {
        if (container.length == 0) {
            this.container = Arrays.copyOf(this.container, 2);
        }
        this.container = Arrays.copyOf(this.container, this.container.length * 2);
    }

    @Override
    public void add(T value) {
        this.container[size++] = value;
        this.modCount++;
        if (size >= container.length) {
            grow();
        }
    }

    @Override
    public T set(int index, T newValue) {
        T temp = get(index);
        container[index] = newValue;
        return temp;
    }

    @Override
    public T remove(int index) {
        T temp = get(index);
        System.arraycopy(container, index + 1, container, index, size);
        container[size] = null;
        size--;
        modCount++;
        return temp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return this.container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            private int point = 0;
            @Override
            public boolean hasNext() {

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
