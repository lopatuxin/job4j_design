package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private String color;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean awd;
    private Brand brand;
    private String[] owners;

    private Car() { }

    public Car(String color, int age, boolean awd, Brand brand, String[] owners) {
        this.color = color;
        this.age = age;
        this.awd = awd;
        this.brand = brand;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "color='" + color + '\''
                + ", age=" + age
                + ", awd=" + awd
                + ", brand=" + brand
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }
}
