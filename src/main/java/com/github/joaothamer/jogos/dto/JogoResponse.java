package com.github.joaothamer.jogos.dto;

import lombok.Data;

@Data
public class JogoResponse {

    private Long id;
    private String nome;
    private String franquia;
    private String classificacao;
    private String fabricante;
}
