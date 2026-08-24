package com.github.joaothamer.jogos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JogoCreateRequest {

    @NotBlank(message = "Nome do jogo é obrigatório")
    @Size(min = 2, message = "Nome do jogo deve ter no mínimo 2 caracteres")
    private String nome;

    @NotBlank(message = "Franquia é obrigatória")
    private String franquia;

    @NotBlank(message = "Classificação é obrigatória")
    private String classificacao;

    @NotBlank(message = "Fabricante é obrigatório")
    private String fabricante;
}
