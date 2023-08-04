package com.api.rinhadebackend.controllers;

import com.api.rinhadebackend.dtos.pessoa.PessoaCreateDto;
import com.api.rinhadebackend.models.Pessoa;
import com.api.rinhadebackend.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.UUID;

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
            .fromPath("/pessoas/{id}")
            .buildAndExpand(pessoa.getId())
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllBySearchTerm(@RequestParam(value = "t") String searchTerm) {
        return ResponseEntity.ok(pessoaService.findAllBySearchTerm(searchTerm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable(name = "id") UUID pessoaId) {
        return ResponseEntity.ok(pessoaService.findById(pessoaId));
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<String> countPeople() {
        return ResponseEntity.ok(pessoaService.countPeople().toString());
    }
}
