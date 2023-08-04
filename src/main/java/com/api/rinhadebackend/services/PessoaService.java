package com.api.rinhadebackend.services;

import com.api.rinhadebackend.dtos.pessoa.PessoaCreateDto;
import com.api.rinhadebackend.models.Pessoa;
import com.api.rinhadebackend.repositories.PessoaRepository;
import com.api.rinhadebackend.services.exceptions.NotFoundException;
import com.api.rinhadebackend.services.exceptions.UnprocessableEntityException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa save(PessoaCreateDto pessoaCreateDto) {
        try {
            return pessoaRepository.save(fromCreateDto(pessoaCreateDto));
        } catch (DataIntegrityViolationException exc) {
            throw new UnprocessableEntityException("Apelido " + pessoaCreateDto.apelido() + " already in use");
        }
    }

    public List<Pessoa> findAllBySearchTerm(String searchTerm) {
        return pessoaRepository.findAllBySearchTerm(searchTerm);
    }

    public Pessoa findById(UUID pessoaId) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);
        return pessoaOptional.orElseThrow(() -> new NotFoundException("Pessoa with identifier " + pessoaId + " was not found"));
    }

    public Long countPeople() {
        return pessoaRepository.count();
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
