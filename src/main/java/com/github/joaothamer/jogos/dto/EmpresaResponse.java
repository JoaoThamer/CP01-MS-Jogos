package com.github.joaothamer.jogos.dto;

import lombok.Data;

@Data
public class EmpresaResponse {

    private Long id;
    private String nome;
    private String pais;
    private String ramo;
    private String sede;
}
