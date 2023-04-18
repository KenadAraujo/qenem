package com.polligonalApps.QEnem.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "QUESTAO")
public class QuestaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToMany
    @JoinTable(name = "CADERNO_QUESTAO",
            joinColumns = {@JoinColumn(name = "FK_QUESTAO")},
            inverseJoinColumns = {@JoinColumn(name = "FK_CADERNO") }
    )
    private Set<CadernoModel> cadernos;

    @OneToMany(mappedBy = "questao")
    private Set<TextoApoioModel> textos;

    @Column(name = "QUANTIDADE_ALTERNATIVA")
    private int quantidadeAlternativa;

    @OneToMany(mappedBy = "questao")
    private Set<AlternativaModel> alternativas;
}
