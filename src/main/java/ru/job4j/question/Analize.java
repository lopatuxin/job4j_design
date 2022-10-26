package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> temp = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        for (User user : previous) {
            if (temp.containsKey(user.getId()) && !temp.containsValue(user.getName())) {
                changed++;
            } else if (!temp.containsKey(user.getId())) {
                deleted++;
            }
        }

        int added = current.size() - previous.size() + deleted;

        return new Info(added, changed, deleted);
    }
}
