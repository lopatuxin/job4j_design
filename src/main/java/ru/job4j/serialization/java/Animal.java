package ru.job4j.serialization.java;

import java.util.Arrays;

public class Animal {

    private final boolean predator;
    private final String name;
    private final int weight;
    private final String[] animals;

    private final Cat cat;

    public Animal(boolean predator, String name, int weight, String[] animals, Cat cat) {
        this.predator = predator;
        this.name = name;
        this.weight = weight;
        this.animals = animals;
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "predator=" + predator
                + ", name='" + name + '\''
                + ", weight=" + weight
                + ", animals=" + Arrays.toString(animals)
                + ", cat=" + cat
                + '}';
    }
}
