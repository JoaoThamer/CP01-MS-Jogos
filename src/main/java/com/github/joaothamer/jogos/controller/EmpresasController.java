package com.github.joaothamer.jogos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.joaothamer.jogos.dto.EmpresaCreateRequest;
import com.github.joaothamer.jogos.dto.EmpresaMapper;
import com.github.joaothamer.jogos.dto.EmpresaResponse;
import com.github.joaothamer.jogos.dto.EmpresaUpdateRequest;
import com.github.joaothamer.jogos.model.Empresa;
import com.github.joaothamer.jogos.service.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/${api.version}/empresas")
public class EmpresasController {

    @Autowired
    private EmpresaService service;

    @Autowired
    private EmpresaMapper empresaMapper;

    @PostMapping
    public ResponseEntity<EmpresaResponse> create(@Valid @RequestBody EmpresaCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaMapper.toDto(
                service.createOrUpdate(
                        empresaMapper.toModel(dtoRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponse> findById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(empresa -> empresaMapper.toDto(empresa))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(empresa -> empresaMapper.toDto(empresa))
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponse> update(@PathVariable Long id,
            @RequestBody EmpresaUpdateRequest dtoRequest) {
        if (service.findById(id).isPresent()) {
            Empresa empresa = empresaMapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(empresaMapper.toDto(
                    service.createOrUpdate(empresa)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
