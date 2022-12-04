package ru.job4j.serialization.java;

public class Cat {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "name='" + name + '\''
                + '}';
    }
}
