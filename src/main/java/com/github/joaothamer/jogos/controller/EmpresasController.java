package com.github.joaothamer.jogos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresasController {

    @GetMapping("/nintendo")
    public String nintendo(){
        return "Nintendo";
    }
}
