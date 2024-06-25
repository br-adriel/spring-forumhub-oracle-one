package br.com.alura.forum_hub.domain.topico.validation;

import br.com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;

public interface ValidadorAtualizacaoTopico {
	void validar(DadosAtualizacaoTopico dados);
}
