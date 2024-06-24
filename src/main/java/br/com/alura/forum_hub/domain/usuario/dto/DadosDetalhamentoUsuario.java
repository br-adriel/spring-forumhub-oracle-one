package br.com.alura.forum_hub.domain.usuario.dto;

import java.util.List;

import br.com.alura.forum_hub.domain.usuario.Usuario;

public record DadosDetalhamentoUsuario(
		Long id,
		String nome,
		String email,
		List<String> perfis) {
	public DadosDetalhamentoUsuario(Usuario usuario) {
		this(
				usuario.getId(),
				usuario.getNome(),
				usuario.getEmail(),
				usuario.getPerfis()
						.stream()
						.map(p -> p.getNome())
						.toList());
	}

}
