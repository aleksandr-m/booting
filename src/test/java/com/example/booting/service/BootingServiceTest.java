package com.example.booting.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.booting.domain.Booting;
import com.example.booting.model.BootingModel;
import com.example.booting.repository.BootingRepository;

@ExtendWith(MockitoExtension.class)
class BootingServiceTest {
    @Mock
    private BootingRepository repo;

    @InjectMocks
    private BootingService service;

    @Test
    void testFindAll() {
        final List<Booting> expected = List.of(new Booting());
        Mockito.when(repo.findAll()).thenReturn(expected);

        final List<Booting> actual = service.findAll();
        MatcherAssert.assertThat(actual, Matchers.is(expected));
    }

    @Test
    void testFindById() {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
        final String name = "this is name";

        final var item = new Booting();
        item.setId(id);
        item.setName(name);

        final var expected = Optional.of(item);

        Mockito.when(repo.findById(id)).thenReturn(expected);

        final Optional<Booting> actual = service.findById(id);
        assertTrue(actual.isPresent());
        MatcherAssert.assertThat(actual.get().getId(), Matchers.is(id));
        MatcherAssert.assertThat(actual.get().getName(), Matchers.is(name));
    }

    @Test
    void testUpdate() {
        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
        final String name = "this is name";

        final var item = new Booting();
        item.setId(id);
        item.setName(name);

        final var expected = Optional.of(item);

        Mockito.when(repo.findById(id)).thenReturn(expected);
        Mockito.when(repo.save(Mockito.any())).thenReturn(item);

        final Optional<Booting> actual = service.update(id, new BootingModel(item));
        assertTrue(actual.isPresent());
        MatcherAssert.assertThat(actual.get().getId(), Matchers.is(id));
        MatcherAssert.assertThat(actual.get().getName(), Matchers.is(name));
    }

    // TODO !!! test save ? somehow ?
//    @Test
//    void testSave() {
//        final UUID id = UUID.fromString("2A68DE09-4499-46C6-A24F-51781AB9C43D");
//        final String name = "this is name";
//
//        final var expected = new Booting();
//        expected.setId(id);
//        expected.setName(name);
//
//        final var model = new BootingModel(expected);
//
//        Mockito.when(repo.save(Mockito.any())).thenReturn(expected);
//
//
//        final Booting actual = service.save(model);
//
//        System.out.println(actual.getId() + "  " + actual.getName());
//
////        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
//
////        MatcherAssert.assertThat(actual, Matchers.is(expected));
//        System.out.println(actual.getName());
//        System.out.println(expected.getName());
//    }
}
