package br.com.alura.forum_hub.domain.usuario.validation;

import br.com.alura.forum_hub.domain.usuario.dto.DadosCadastroUsuario;

public interface ValidadorCadastroUsuario {
	void validar(DadosCadastroUsuario dados);
}
