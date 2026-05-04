package com.github.joaothamer.jogos.controller;

import com.github.joaothamer.jogos.model.Jogo;
import com.github.joaothamer.jogos.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
public class JogosController {

    @Autowired
    private JogoRepository repository;

    @PostMapping
    public ResponseEntity<Jogo> create (@RequestBody Jogo jogo){

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(jogo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> findById(@PathVariable Long id){

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> findAll(){

        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> update(@PathVariable Long id, @RequestBody Jogo jogo){

        Optional<Jogo> optJogo = repository.findById(id);

        if (optJogo.isPresent()){
            jogo.setId(id);
            Jogo jogoAlterado = repository.save(jogo);
            return ResponseEntity.ok(jogoAlterado);
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
