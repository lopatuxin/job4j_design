package ru.job4j.seacher;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Searcher {
    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(new String[] {"-d=.", "-n=Analysis.java", "-t=name", "-o=log.txt"});
        search(argsName);
    }

    public static void search(ArgsName argsName) throws IOException {
        Path root = Path.of(argsName.get("d"));
        String out = argsName.get("o");
        String nameFile = argsName.get("n");
        String typeSearch = argsName.get("t");
        Predicate<Path> condition = null;
        if ("mask".equals(typeSearch)) {
            String name = nameFile;
            name = name.replace(".", "[.]");
            name = name.replace("*", ".+");
            name = name.replace("?", ".");
            String finalName = name;
            condition = path -> path.toFile().getName().matches(finalName);
        } else if ("name".equals(typeSearch)) {
            condition = path -> path.toFile().getName().equals(nameFile);
        } else if ("regex".equals(typeSearch)) {
            condition = path -> path.toFile().getName().matches(nameFile);
        }
        var searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        List<Path> pathList = searcher.getPaths();
        writer(out, pathList);
    }

    public static void writer(String out, List<Path> list) {
        if ("stdout".equals(out)) {
            for (Path path : list) {
                System.out.println(path.toString());
            }
        } else {
            Path path = Paths.get(out);
            try (PrintWriter writer = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(path.toString())))) {
                list.forEach(writer :: println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void validate(ArgsName args) {
        Path path = Path.of(args.get("d"));
        if (!Files.exists(path) && !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER");
        }
        if (!"mask".equals(args.get("t")) && !"name".equals(args.get("t")) && !"regex".equals(args.get("t"))) {
            throw new IllegalArgumentException("Use mask or name or regex");
        }
        if (!"stdout".equals(args.get("o")) && !args.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("output not specified as .txt or stdout");
        }
    }
}
