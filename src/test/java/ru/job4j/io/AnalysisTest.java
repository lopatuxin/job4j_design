package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("source1.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target1.txt").toFile();
        StringBuilder rsl = new StringBuilder();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl :: append);
        }
        assertThat("10:57:01;10:59:0111:01:02;11:02:02").isEqualTo(rsl.toString());
    }
}