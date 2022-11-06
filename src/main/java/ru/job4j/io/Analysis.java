package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analysis {

    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            BufferedReader in = new BufferedReader(new FileReader(source));
            boolean flag = true;
            while (in.ready()) {
                String line = in.readLine();
                if (flag && (line.contains("400") || line.contains("500"))) {
                    flag = false;
                    out.print(line.split(" ")[1]);
                    out.print(";");
                }
                if (!flag && (line.contains("200") || line.contains("300"))) {
                    flag = true;
                    out.println(line.split(" ")[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("source.txt", "target.txt");
    }
}
