package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {

        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    final String s = Integer.toString(i * j);
                    out.write(s.getBytes());
                    out.write(" ".getBytes());
                    if (j == 10) {
                        out.write(System.lineSeparator().getBytes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
