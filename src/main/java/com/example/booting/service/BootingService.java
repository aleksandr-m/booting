package com.example.booting.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.booting.domain.Booting;
import com.example.booting.model.BootingModel;
import com.example.booting.repository.BootingRepository;

@Service
public class BootingService {
    private final BootingRepository bootingRepository;

    public BootingService(BootingRepository bootingRepository) {
        this.bootingRepository = bootingRepository;
    }

    public List<Booting> findAll() {
        return bootingRepository.findAll();
    }

    public Optional<Booting> findById(UUID id) {
        return bootingRepository.findById(id);
    }

    public Booting save(BootingModel bootingModel) {
        return bootingRepository.save(bootingModel.toEntity());
    }

    public Optional<Booting> update(UUID id, BootingModel bootingModel) {
        return bootingRepository.findById(id)
                .map(booting -> {
                    var entity = bootingModel.update(booting);
                    return bootingRepository.save(entity);
                });
    }

    public void delete(UUID id) {
        bootingRepository.findById(id)
            .ifPresent(entity -> bootingRepository.delete(entity));
    }
}
