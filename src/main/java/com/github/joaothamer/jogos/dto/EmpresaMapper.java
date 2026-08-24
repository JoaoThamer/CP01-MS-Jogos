package com.github.joaothamer.jogos.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.github.joaothamer.jogos.model.Empresa;

@Component
public class EmpresaMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Empresa toModel(EmpresaCreateRequest dto) {
        return modelMapper.map(dto, Empresa.class);
    }

    public EmpresaResponse toDto(Empresa entity) {
        return modelMapper.map(entity, EmpresaResponse.class);
    }

    public Empresa toModel(Long id, EmpresaUpdateRequest dto) {
        Empresa empresa = modelMapper.map(dto, Empresa.class);
        empresa.setId(id);
        return empresa;
    }
}
