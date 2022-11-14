package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if ((value) == null) {
            throw new IllegalArgumentException("Parameter is null");
        }
        return value;
    }

    private void validate(String line) {
        if (!line.startsWith("-")) {
            throw new IllegalArgumentException("The string does not contain -");
        }
        if (!line.contains("=")) {
            throw new IllegalArgumentException("The string does not contain =");
        }
        if (line.split("=", 2)[0].substring(1).isEmpty()) {
            throw new IllegalArgumentException("The string does not contain key");
        }
        if (line.split("=", 2)[1].isEmpty()) {
            throw new IllegalArgumentException("The string does not contain value");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Not parameters");
        }
        for (String arg : args) {
            validate(arg);
            values.put(arg.split("=", 2)[0].substring(1), arg.split("=", 2)[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        return argsName;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xms"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));

        System.out.println("-=xxxx".split("=")[0].substring(1).isEmpty());
    }
}
