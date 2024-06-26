package br.com.alura.forum_hub.domain.resposta.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.resposta.dto.DadosCadastroResposta;
import br.com.alura.forum_hub.domain.topico.TopicoRepository;

@Component
public class TopicoValido implements ValidadorCadastroResposta {
	@Autowired
	private TopicoRepository topicoRepository;

	@Override
	public void validar(DadosCadastroResposta dados) {
		validar(dados.topico());
	}

	private void validar(Long id) {
		topicoRepository
				.findById(id)
				.orElseThrow(() -> new ValidacaoException("Tópico inválido"));
	}
}
