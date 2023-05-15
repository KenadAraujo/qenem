package com.polligonalApps.QEnem.models;

import com.polligonalApps.QEnem.domain.dto.Caderno;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CADERNO")
public class CadernoModel implements AbstractModel<Caderno>{

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

    public CadernoModel(Caderno caderno) {
        this.id = caderno.id();
        this.ano = Year.of(caderno.ano());
        this.banca = new BancaModel(caderno.banca());
        this.questoes = new HashSet<>();
    }

    @Override
    public Caderno toRecord() {
        return new Caderno(this.id,this.ano.getValue(),this.banca.toRecord());
    }
}
