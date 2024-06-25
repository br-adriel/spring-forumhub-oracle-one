package br.com.alura.forum_hub.domain.usuario.validation;

import br.com.alura.forum_hub.domain.usuario.dto.DadosAtualizacaoUsuario;

public interface ValidadorAtualizacaoUsuario {
	void validar(DadosAtualizacaoUsuario dados);
}
