package com.example.booting.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.booting.domain.Booting;
import com.example.booting.model.BootingModel;
import com.example.booting.service.BootingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(BootingController.class)
class BootingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BootingService service;

    @Test
    void testAll() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
        final String name = "this is name";

        final var item = new Booting();
        item.setId(id);
        item.setName(name);

        Mockito.when(service.findAll()).thenReturn(List.of(item));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/booting"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Matchers.is(id.toString())))
                .andExpect(jsonPath("$[0].name", Matchers.is(name)));
    }

    @Test
    void testOne() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
        final String name = "this is name";

        final var item = new Booting();
        item.setId(id);
        item.setName(name);

        Mockito.when(service.findById(id)).thenReturn(Optional.of(item));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/booting/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(id.toString())))
                .andExpect(jsonPath("$.name", Matchers.is(name)));
    }

    @Test
    void testOneNotFound() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");

        Mockito.when(service.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/booting/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
        final String name = "this is name";

        final var item = new Booting();
        item.setId(id);
        item.setName(name);

        Mockito.when(service.save(Mockito.any())).thenReturn(item);

        final var modelString = objectMapper.writeValueAsString(new BootingModel(item));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/booting")
                            .content(modelString)
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(id.toString())))
                .andExpect(jsonPath("$.name", Matchers.is(name)));
    }

    @Test
    void testSaveValidationError() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
        final String name = "this is name";

        final var item = new Booting();
        item.setId(id);
        item.setName(name);

        Mockito.when(service.save(Mockito.any())).thenReturn(item);

        final var model = new BootingModel(item);
        model.setName(null);

        final var modelString = objectMapper.writeValueAsString(model);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/booting")
                            .content(modelString)
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdate() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
        final String name = "this is name";

        final var item = new Booting();
        item.setId(id);
        item.setName(name);

        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(Optional.of(item));

        final var modelString = objectMapper.writeValueAsString(new BootingModel(item));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/booting/" + id)
                            .content(modelString)
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(id.toString())))
                .andExpect(jsonPath("$.name", Matchers.is(name)));
    }

    @Test
    void testUpdateValidationError() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
        final String name = "this is name";

        final var item = new Booting();
        item.setId(id);
        item.setName(name);

        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(Optional.of(item));

        final var model = new BootingModel(item);
        model.setName(null);

        final var modelString = objectMapper.writeValueAsString(model);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/booting/" + id)
                            .content(modelString)
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateNotFound() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");

        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());

        final var model = new BootingModel();
        model.setName("this is name");

        final var modelString = objectMapper.writeValueAsString(model);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/booting/" + id)
                            .content(modelString)
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDelete() throws Exception {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");

        Mockito.doNothing().when(service).delete(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/booting/"+ id))
                .andExpect(status().isOk());
    }
}
