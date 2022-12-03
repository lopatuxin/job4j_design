package ru.job4j.serialization.java;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        JSONObject jsonCat = new JSONObject("{\"name\":\"Tor\"}");
        List<String> list = new ArrayList<>();
        list.add("Tiger");
        list.add("Cat");
        JSONArray jsonAnimals = new JSONArray(list);
        final Animal animal = new Animal(true, "Tiger",
                100, new String[] {"Tiger", "Cat"}, new Cat("Tor"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("predator", animal.isPredator());
        jsonObject.put("name", animal.getName());
        jsonObject.put("weight", animal.getWeight());
        jsonObject.put("animals", jsonAnimals);
        jsonObject.put("cat", jsonCat);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(animal).toString());
    }
}
