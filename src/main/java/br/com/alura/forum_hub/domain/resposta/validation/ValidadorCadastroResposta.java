package br.com.alura.forum_hub.domain.resposta.validation;

import br.com.alura.forum_hub.domain.resposta.dto.DadosCadastroResposta;

public interface ValidadorCadastroResposta {
	void validar(DadosCadastroResposta dados);
}
