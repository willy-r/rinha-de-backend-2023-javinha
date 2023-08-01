package com.api.rinhadebackend.controllers;

import com.api.rinhadebackend.dtos.pessoa.PessoaCreateDto;
import com.api.rinhadebackend.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid PessoaCreateDto pessoaCreateDto) {
        var pessoa = pessoaService.save(pessoaCreateDto);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(pessoa.getId())
            .toUri();
        return ResponseEntity.created(uri).build();
    }
}
