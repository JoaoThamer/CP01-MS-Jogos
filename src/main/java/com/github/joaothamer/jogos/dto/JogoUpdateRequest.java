package com.github.joaothamer.jogos.dto;

import lombok.Data;

@Data
public class JogoUpdateRequest {

    private String nome;
    private String franquia;
    private String classificacao;
    private String fabricante;
}
