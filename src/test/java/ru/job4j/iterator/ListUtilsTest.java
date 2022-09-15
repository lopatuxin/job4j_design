package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> predicate = x -> x < 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenReplaceIf() {
        Predicate<Integer> predicate = x -> x < 3;
        ListUtils.replaceIf(input, predicate, 5);
        assertThat(input).hasSize(2).containsSequence(5, 3);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 4));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(3);
    }
}