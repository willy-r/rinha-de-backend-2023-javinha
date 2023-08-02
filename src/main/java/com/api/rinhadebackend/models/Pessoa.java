package com.api.rinhadebackend.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 32)
    private String apelido;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false)
    private LocalDate nascimento;
    @ElementCollection
    @CollectionTable(name = "pessoas_stacks", joinColumns = @JoinColumn(name = "pessoa_id"))
    @Column(nullable = true, length = 32)
    private List<String> stack;

    public Pessoa() {
    }

    public Pessoa(UUID id, String apelido, String nome, LocalDate nascimento, List<String> stack) {
        this.id = id;
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }
}
