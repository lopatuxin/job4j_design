package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenUsernameIsAnton() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Anton");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsAnton() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        store.add(new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Anton");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        store.replace("1", new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        store.replace("10", new Role("10", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Anton");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsAnton() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Anton");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        boolean rsl = store.replace("1", new Role("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        boolean rsl = store.replace("10", new Role("10", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Anton"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}