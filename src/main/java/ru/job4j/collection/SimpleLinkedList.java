package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
public class SimpleLinkedList<E> implements LinkedList<E> {
    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    private int size = 0;
    private int modCount;
    private Node<E> first = null;
    private Node<E> last = null;

    @Override
    public void add(E value) {
        Node newNode = new Node<>(value);

        if (size == 0) {
            newNode.next = null;
            first = newNode;
        } else {
            last.next = newNode;
            newNode.next = null;
        }
        last = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int count = 0;
        Node<E> tmp = first;
        while (count < index) {
            tmp = tmp.next;
            count++;
        }
        return tmp.item;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            final int expectedModCount = modCount;
            private Node<E> lastReturned;
            private Node<E> next = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = next;
                next = next.next;
                return lastReturned.item;
            }
        };
    }
}
