package br.com.alura.forum_hub.domain.topico.validation;

import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;

@Component
public class TituloDiferenteDeMensagem implements ValidadorCadastroTopico {
	@Override
	public void validar(DadosCadastroTopico dados) {
		if (dados.titulo().equalsIgnoreCase(dados.mensagem())) {
			throw new ValidacaoException("Titulo e mensagem devem ser diferentes");
		}
	}
}
