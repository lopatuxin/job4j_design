package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void whatsThis() {
        Box box = new Box(6, -2);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void isExistTrue() {
        Box box = new Box(6, 5);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(6, -1);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void getAreaDefault() {
        Box box = new Box(7, 5);
        assertThat(box.getArea()).isEqualTo(0);
    }

    @Test
    void getArea4() {
        Box box = new Box(6, 5);
        assertThat(box.getArea()).isEqualTo(150);
    }
}