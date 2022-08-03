package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index = -1;
    private boolean flag = false;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (!flag) {
            for (int i = index; i < data.length - 1; i++) {
                if (data[i + 1] % 2 == 0) {
                    index = i + 1;
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            flag = false;
            return data[index];
        }
    }
}
