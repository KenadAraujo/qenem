package com.polligonalApps.QEnem.domain.dto;

public record Banca(Long id,String nome) {
    public String getNome(String nome){
        return nome.toUpperCase();
    }
}

