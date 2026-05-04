package com.github.joaothamer.jogos.controller;


import com.github.joaothamer.jogos.model.Empresa;
import com.github.joaothamer.jogos.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresasController {

    @Autowired
    private EmpresaRepository repository;

    @PostMapping
    public ResponseEntity<Empresa> create (@RequestBody Empresa empresa){

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(empresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> findById(@PathVariable Long id){

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> findAll(){

        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@PathVariable Long id, @RequestBody Empresa empresa){

        Optional<Empresa> optEmpresa = repository.findById(id);

        if (optEmpresa.isPresent()){
            empresa.setId(id);
            Empresa empresaAlterado = repository.save(empresa);
            return ResponseEntity.ok(empresaAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
