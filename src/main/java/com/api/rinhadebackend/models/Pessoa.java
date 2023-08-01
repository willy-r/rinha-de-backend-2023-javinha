package com.api.rinhadebackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Pessoa {
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
    @CollectionTable(name = "pessoa_stack", joinColumns = @JoinColumn(name = "pessoa_id"))
    private List<String> stack;
}
