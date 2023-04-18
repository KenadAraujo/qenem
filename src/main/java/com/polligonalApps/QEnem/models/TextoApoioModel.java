package com.polligonalApps.QEnem.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TEXTO_APOIO")
public class TextoApoioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORDEM")
    private int ordem;

    @Column(name = "TEXTO", nullable = true)
    private String texto;

    @Column(name = "IMAGEM", nullable = true)
    private byte imagem;

    @ManyToOne
    @JoinColumn(name = "QUESTAO_ID")
    private QuestaoModel questao;
}
