package com.example.booting.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.booting.domain.Booting;

class BootingModelTest {
    @Test
    void testCreating() {
        final var item = new Booting();
        item.setId(UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D"));
        item.setName("this is name");

        final BootingModel actual = new BootingModel(item);
        assertEquals(item.getName(), actual.getName());
    }

    @Test
    void testUpdate() {
        final BootingModel model = new BootingModel();
        model.setName("this is name");

        final var item = new Booting();
        item.setName("old name");

        final Booting actual = model.update(item);
        assertEquals(item, actual);
        assertEquals(model.getName(), actual.getName());
        assertEquals(item.getName(), actual.getName());
    }

    @Test
    void testToEntity() {
        final BootingModel model = new BootingModel();
        model.setName("this is name");

        final Booting actual = model.toEntity();
        assertEquals(model.getName(), actual.getName());
    }
}
