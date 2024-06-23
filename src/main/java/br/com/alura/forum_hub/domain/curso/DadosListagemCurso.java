package br.com.alura.forum_hub.domain.curso;

public record DadosListagemCurso(
		Long id,
		String nome,
		CategoriaCurso categoria) {
	public DadosListagemCurso(Curso curso) {
		this(curso.getId(), curso.getNome(), curso.getCategoria());
	}
}
