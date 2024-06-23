package br.com.alura.forum_hub.domain.curso.dto;

import br.com.alura.forum_hub.domain.curso.CategoriaCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCurso(
		@NotBlank String nome,
		@NotNull CategoriaCurso categoria) {
}
