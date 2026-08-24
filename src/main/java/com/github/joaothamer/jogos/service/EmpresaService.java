package com.github.joaothamer.jogos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.joaothamer.jogos.model.Empresa;
import com.github.joaothamer.jogos.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    public Empresa createOrUpdate(Empresa empresa) {
        return repository.save(empresa);
    }

    public Optional<Empresa> findById(Long id) {
        return repository.findById(id);
    }

    public List<Empresa> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
