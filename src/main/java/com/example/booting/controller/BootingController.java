package com.example.booting.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booting.domain.Booting;
import com.example.booting.model.BootingModel;
import com.example.booting.service.BootingService;

@RestController
@RequestMapping("/api/booting")
public class BootingController {
    private final BootingService bootingService;

    public BootingController(BootingService bootingService) {
        this.bootingService = bootingService;
    }

    @GetMapping
    public List<Booting> all() {
        return bootingService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Booting> one(@PathVariable UUID id) {
        return bootingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Booting save(@RequestBody @Validated BootingModel bootingModel) {
        return bootingService.save(bootingModel);
    }

    @PutMapping("{id}")
    public ResponseEntity<Booting> update(@PathVariable UUID id, @RequestBody @Validated BootingModel bootingModel) {
        return bootingService.update(id, bootingModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        bootingService.delete(id);
    }
}
