package com.example.booting.model;

import com.example.booting.domain.Booting;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BootingModel {
    @NotBlank
    private String name;

    public BootingModel(Booting booting) {
        this.name = booting.getName();
    }

    public Booting update(Booting booting) {
        booting.setName(name);
        return booting;
    }

    public Booting toEntity() {
        Booting booting = new Booting();
        booting.setName(name);
        return booting;
    }
}
