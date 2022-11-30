package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Animal animal = new Animal(true, "Tiger",
                100, new String[] {"Tiger", "Cat"}, new Cat("Tor"));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(animal));

        final String animalString =
                "{"
                + "\"predator\":true,"
                + "\"name\":Cat,"
                + "\"weight\":10,"
                + "\"animals\":"
                + "[\"Tiger\",\"Cat\"],"
                + "\"cat\":"
                + "{"
                + "\"name\":\"Tor\""
                + "}"
                + "}";
        final Animal animalMod = gson.fromJson(animalString, Animal.class);
        System.out.println(animalMod);
    }
}
