package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            Scanner scan = new Scanner(in);
            while (scan.hasNext()) {
                int x = scan.nextInt();
                if (x % 2 == 0) {
                    System.out.println(x);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
