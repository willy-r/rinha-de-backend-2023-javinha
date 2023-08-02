package com.api.rinhadebackend.dtos.pessoa;

import com.api.rinhadebackend.dtos.pessoa.validators.ValidArrayStringLength;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record PessoaCreateDto(
    @NotNull
    @Length(min = 1, max = 32)
    String apelido,
    @NotNull
    @Length(min = 1, max = 100)
    String nome,
    @NotNull
    @Pattern(regexp = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Invalid date format. The date should be in the format 'YYYY-MM-DD'")
    String nascimento,
    @Size(min = 1)
    @ValidArrayStringLength
    List<String> stack
) {
}
