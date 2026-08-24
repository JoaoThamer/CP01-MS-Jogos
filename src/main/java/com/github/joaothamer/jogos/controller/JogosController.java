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

import com.github.joaothamer.jogos.dto.JogoCreateRequest;
import com.github.joaothamer.jogos.dto.JogoMapper;
import com.github.joaothamer.jogos.dto.JogoResponse;
import com.github.joaothamer.jogos.dto.JogoUpdateRequest;
import com.github.joaothamer.jogos.model.Jogo;
import com.github.joaothamer.jogos.service.JogoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/${api.version}/jogos")
public class JogosController {

    @Autowired
    private JogoService service;

    @Autowired
    private JogoMapper jogoMapper;

    @PostMapping
    public ResponseEntity<JogoResponse> create(@Valid @RequestBody JogoCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoMapper.toDto(
                service.createOrUpdate(
                        jogoMapper.toModel(dtoRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoResponse> findById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(jogo -> jogoMapper.toDto(jogo))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<JogoResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(jogo -> jogoMapper.toDto(jogo))
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoResponse> update(@PathVariable Long id,
            @RequestBody JogoUpdateRequest dtoRequest) {
        if (service.findById(id).isPresent()) {
            Jogo jogo = jogoMapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(jogoMapper.toDto(
                    service.createOrUpdate(jogo)));
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
