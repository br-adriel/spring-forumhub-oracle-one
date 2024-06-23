package br.com.alura.forum_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCurso(
		@NotBlank String nome,
		@NotNull CategoriaCurso categoria) {
}
