package com.polligonalApps.QEnem.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "QUESTAO")
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTIDADE_ALTERNATIVA")
    private int quantidadeAlternativa;

    @ManyToMany
    @JoinTable(name = "CADERNO_QUESTAO",
            joinColumns = {@JoinColumn(name = "FK_QUESTAO")},
            inverseJoinColumns = {@JoinColumn(name = "FK_CADERNO") }
    )
    private Set<Caderno> cadernos;

    @OneToMany(mappedBy = "questao")
    private Set<TextoApoio> textos;

    @OneToMany(mappedBy = "questao")
    private Set<Alternativa> alternativas;
}
