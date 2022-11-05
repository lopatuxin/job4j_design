package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                if (!line.contains("=") || line.startsWith("=") || line.endsWith("=") || Objects.equals("=", line)) {
                    throw new IllegalArgumentException();
                }
                String[] lines = line.split("=");
                if (lines.length > 2) {
                    values.put(lines[lines.length - 2], lines[lines.length - 1]);
                } else {
                    values.put(lines[0], lines[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        return "Config{"
                + "path='" + path + '\''
                + ", values=" + values
                + '}';
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
    }
}
