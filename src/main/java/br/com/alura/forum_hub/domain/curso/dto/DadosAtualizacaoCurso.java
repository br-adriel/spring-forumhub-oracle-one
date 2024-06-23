package br.com.alura.forum_hub.domain.curso.dto;

import br.com.alura.forum_hub.domain.curso.CategoriaCurso;

public record DadosAtualizacaoCurso(
		String nome,
		CategoriaCurso categoria) {
}
