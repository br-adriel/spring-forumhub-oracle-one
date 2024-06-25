package br.com.alura.forum_hub.domain.topico.validation;

import br.com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;

public interface ValidadorCadastroTopico {
	void validar(DadosCadastroTopico dados);
}
