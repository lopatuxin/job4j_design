package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int searchIndex(K key) {
        int result = -1;
        for (int i = 0; i < capacity - 1; i++) {
            if (key == null && table[i] != null && table[i].key == null) {
                result = i;
                break;
            } else if (key != null && table[i] != null && table[i].key != null && table[i].key.hashCode() == key.hashCode()) {
                if (table[i].key.equals(key)) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        if (key == null && table[0] == null) {
            table[0] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        } else if (key != null) {
            int h = hash(key.hashCode());
            int i = indexFor(h);
            if (table[i] == null) {
                table[i] = new MapEntry<>(key, value);
                count++;
                modCount++;
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    private int hash(int hashCode) {
        int h = hashCode;
        return hashCode ^ h >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] temp = new MapEntry[count + 1];
        int index = 0;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                temp[index++] = table[i];
            }
        }
        capacity = capacity * 2;
        table = new MapEntry[capacity];
        System.arraycopy(temp, 0, table, 0, count + 1);
    }

    @Override
    public V get(K key) {
        int index = searchIndex(key);
        return index == -1 ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int index = searchIndex(key);
        boolean result = false;
        if (index > -1) {
            table[index] = null;
            result = true;
            modCount++;
            count--;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "MapEntry{" + "key="
                    + key
                    + ", value="
                    + value
                    + '}';
        }
    }

}
