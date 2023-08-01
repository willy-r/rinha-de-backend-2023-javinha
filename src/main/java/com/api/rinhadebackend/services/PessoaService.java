package com.api.rinhadebackend.services;

import com.api.rinhadebackend.dtos.pessoa.PessoaCreateDto;
import com.api.rinhadebackend.models.Pessoa;
import com.api.rinhadebackend.repositories.PessoaRepository;
import com.api.rinhadebackend.services.exceptions.UnprocessableEntityException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa save(PessoaCreateDto pessoaCreateDto) {
        if (pessoaRepository.existsByApelido(pessoaCreateDto.apelido())) {
            throw new UnprocessableEntityException("Apelido " + pessoaCreateDto.apelido() + " already in use");
        }
        return pessoaRepository.save(fromCreateDto(pessoaCreateDto));
    }

    public Pessoa fromCreateDto(PessoaCreateDto pessoaCreateDto) {
        var pessoa = new Pessoa();
        pessoa.setApelido(pessoaCreateDto.apelido());
        pessoa.setNome(pessoaCreateDto.nome());
        pessoa.setNascimento(LocalDate.parse(pessoaCreateDto.nascimento()));
        pessoa.setStack(pessoaCreateDto.stack());
        return pessoa;
    }
}
