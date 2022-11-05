package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("Anton");
    }
    @Test
    void whenTwoKey() {
        String path = "app1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Anton");
    }
    @Test ()
    void whenException() throws IllegalArgumentException{
        String path = "app2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }
}