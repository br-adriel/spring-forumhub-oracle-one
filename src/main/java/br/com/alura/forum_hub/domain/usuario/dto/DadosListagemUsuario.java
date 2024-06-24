package br.com.alura.forum_hub.domain.usuario.dto;

import br.com.alura.forum_hub.domain.usuario.Usuario;

public record DadosListagemUsuario(
		Long id,
		String nome,
		String email) {
	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getEmail());
	}
}
