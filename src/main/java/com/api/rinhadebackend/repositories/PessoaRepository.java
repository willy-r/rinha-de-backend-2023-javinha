package com.api.rinhadebackend.repositories;

import com.api.rinhadebackend.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    boolean existsByApelido(String apelido);
    @Query(
        nativeQuery = true,
        value = "SELECT DISTINCT p.* FROM pessoas p " +
        "JOIN pessoas_stacks ps ON p.id = ps.pessoa_id " +
        "WHERE LOWER(p.apelido) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(p.nome) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(ps.stack) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
        "LIMIT 50"
    )
    List<Pessoa> findAllBySearchTerm(String searchTerm);
}
