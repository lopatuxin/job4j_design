package ru.job4j.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public boolean revert() {
        boolean rsl = false;
        if (!isEmpty() && head.next != null) {
            tail = head;
            Node<T> current = head.next;
            head.next = null;
            while (current != null) {
                Node<T> next = current.next;
                current.next = head;
                head = current;
                current = next;
            }
            rsl = true;
        }
        return rsl;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        tail = node;
    }

    public T deleteFirst() {

        if (head == null) {
            throw new NoSuchElementException();
        }
        T temp = head.value;
        Node<T> next = head.next;
        head.next = null;
        head.value = null;
        head = next;

        return temp;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
