package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        files.putIfAbsent(fileProperty, new ArrayList<>());
        files.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicate() {
        List<Path> list = new ArrayList<>();
        files.values().stream()
                .filter(paths -> paths.size() > 1)
                .forEach(list :: addAll);
        return list;
    }
}
