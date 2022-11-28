package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Anton";
        int age = 33;
        LOG.debug("Name : {}, Age : {}", name, age);
        double d = 5.45;
        LOG.debug("Double : {}", d);
        char ch = 'A';
        LOG.debug("Char : {}", ch);
        float f = 3.34f;
        LOG.debug("Float : {}", f);
        boolean b = false;
        LOG.debug("Boolean : {}", b);
        byte by = 23;
        LOG.debug("Byte : {}", by);
        short sh = 34;
        LOG.debug("Short : {}", sh);
        long l = 45;
        LOG.debug("Long : {}", l);
    }
}
