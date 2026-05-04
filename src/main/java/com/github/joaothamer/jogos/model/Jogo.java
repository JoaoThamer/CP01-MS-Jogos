package com.github.joaothamer.jogos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jogos")
public class Jogo {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "franquia", nullable = false)
    private String franquia;

    @Column(name = "classificacao", nullable = false)
    private String classificacao;

    @Column(name = "fabricante", nullable = false)
    private String fabricante;

}
