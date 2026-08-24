package com.github.joaothamer.jogos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.joaothamer.jogos.model.Jogo;
import com.github.joaothamer.jogos.repository.JogoRepository;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

    public Jogo createOrUpdate(Jogo jogo) {
        return repository.save(jogo);
    }

    public Optional<Jogo> findById(Long id) {
        return repository.findById(id);
    }

    public List<Jogo> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
