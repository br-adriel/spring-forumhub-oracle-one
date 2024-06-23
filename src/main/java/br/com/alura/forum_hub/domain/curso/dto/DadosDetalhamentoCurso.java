package br.com.alura.forum_hub.domain.curso.dto;

import br.com.alura.forum_hub.domain.curso.CategoriaCurso;
import br.com.alura.forum_hub.domain.curso.Curso;

public record DadosDetalhamentoCurso(
		Long id,
		String nome,
		CategoriaCurso categoria) {
	public DadosDetalhamentoCurso(Curso curso) {
		this(curso.getId(), curso.getNome(), curso.getCategoria());
	}
}
