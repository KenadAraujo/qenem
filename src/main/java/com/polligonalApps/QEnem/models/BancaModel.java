package com.polligonalApps.QEnem.models;

import com.polligonalApps.QEnem.domain.dto.Banca;
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
@Table(name = "BANCA")
public class BancaModel extends AbstractModel<Banca>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @OneToMany(mappedBy = "banca")
    private Set<CadernoModel> cadernos;

    public BancaModel(Banca banca) {
        this.nome = banca.nome();
    }

    @Override
    public Banca toRecord() {
        return new Banca(this.id,this.nome);
    }
}
