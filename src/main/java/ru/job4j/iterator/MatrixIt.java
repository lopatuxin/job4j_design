package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean flag = true;
        if (row >= data[column].length) {
            row = 0;
            column++;
        }
        if (column >= data.length) {
            flag = false;
        }
        while (flag && data[column].length == 0) {
            column++;
            row = 0;
            if (column >= data.length) {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[column][row++];
    }

    public static void main(String[] args) {
        int[][] dat = {
                {1}, {2, 3}, {}, {}, {4}
        };
        System.out.println(dat);
    }
}
