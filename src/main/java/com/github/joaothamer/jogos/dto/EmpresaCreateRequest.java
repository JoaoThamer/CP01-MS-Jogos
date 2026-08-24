package com.github.joaothamer.jogos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpresaCreateRequest {

    @NotBlank(message = "Nome da empresa é obrigatório")
    @Size(min = 2, message = "Nome da empresa deve ter no mínimo 2 caracteres")
    private String nome;

    @NotBlank(message = "País é obrigatório")
    private String pais;

    @NotBlank(message = "Ramo é obrigatório")
    private String ramo;

    @NotBlank(message = "Sede é obrigatória")
    private String sede;
}
