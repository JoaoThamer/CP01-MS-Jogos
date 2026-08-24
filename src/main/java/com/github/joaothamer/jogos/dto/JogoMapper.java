package com.github.joaothamer.jogos.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.github.joaothamer.jogos.model.Jogo;

@Component
public class JogoMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Jogo toModel(JogoCreateRequest dto) {
        return modelMapper.map(dto, Jogo.class);
    }

    public JogoResponse toDto(Jogo entity) {
        return modelMapper.map(entity, JogoResponse.class);
    }

    public Jogo toModel(Long id, JogoUpdateRequest dto) {
        Jogo jogo = modelMapper.map(dto, Jogo.class);
        jogo.setId(id);
        return jogo;
    }
}
