package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "Закончить";
    private static final String STOP = "Стоп";
    private static final String CONTINUE = "Продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> phrases = new ArrayList<>();
    private List<String> getPhrases = new ArrayList<>();
    private String phrase;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        getPhrases = readPhrases(botAnswers);
        while (!OUT.equals(phrase)) {
            System.out.println("Enter the word : ");
            phrase = scanner.nextLine();
            String answer = getPhrases.get(random.nextInt(0, getPhrases.size() - 1));
            phrases.add("<Фраза> " + phrase);
            phrases.add("<Ответ> " + answer);
            validate();
            System.out.println(answer);
        }
    }

    public void validate() {
        if (OUT.equals(phrase)) {
            saveLog(phrases);
        }
        if (STOP.equals(phrase)) {
            while (!CONTINUE.equals(phrase)) {
                phrase = scanner.nextLine();
                phrases.add("<Фраза> " + phrase);
            }
        }
    }

    private List<String> readPhrases(String path) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
             rsl = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)
                ))) {
            log.forEach(out ::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("log1.txt", "phrases.txt");
        cc.run();
    }
}
