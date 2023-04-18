package com.polligonalApps.QEnem.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CADERNO")
public class CadernoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ANO")
    private Year ano;

    @ManyToOne
    @JoinColumn(name = "BANCA_ID",nullable = false)
    private BancaModel banca;

    @ManyToMany(mappedBy = "cadernos")
    private Set<QuestaoModel> questoes;
}
