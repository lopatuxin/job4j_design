package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user = new User("Anton", 10, birthday);
        User user1 = new User("Anton", 10, birthday);
        Map<User, Object> map = new HashMap<>(16);
        map.put(user, new Object());
        map.put(user1, new Object());
        int hashcode = user.hashCode();
        int hashcode1 = user1.hashCode();
        System.out.println("user " + hashcode);
        System.out.println("user1 " + hashcode1);
        int hash = hashcode ^ (hashcode >>> 16);
        int hash1 = hashcode1 ^ (hashcode1 >>>  16);
        System.out.println("hash " + hash);
        System.out.println("hash1 " + hash1);

        int bucket = hash & 15;
        int bucket1 = hash1 & 15;

        System.out.println("bucket " + bucket);
        System.out.println(("bucket1 " + bucket1));

    }
}
