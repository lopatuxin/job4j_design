package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "brand")
public class Brand {
    @XmlAttribute
    private String name;

    public Brand() { }

    public Brand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{"
                + "name='" + name + '\''
                + '}';
    }
}
