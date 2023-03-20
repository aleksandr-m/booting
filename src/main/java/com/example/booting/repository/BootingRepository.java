package com.example.booting.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booting.domain.Booting;

public interface BootingRepository extends JpaRepository<Booting, UUID> {

}
