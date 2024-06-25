package br.com.alura.forum_hub.domain.topico.validation;

import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import br.com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;

@Component
public class TituloDiferenteDeMensagem implements ValidadorCadastroTopico, ValidadorAtualizacaoTopico {
	@Override
	public void validar(DadosCadastroTopico dados) {
		validar(dados.titulo(), dados.mensagem());
	}

	@Override
	public void validar(DadosAtualizacaoTopico dados) {
		if (dados.titulo() != null && dados.mensagem() != null) {
			validar(dados.titulo(), dados.mensagem());
		}
	}

	public static void validar(String titulo, String mensagem) {
		if (titulo.equalsIgnoreCase(mensagem)) {
			throw new ValidacaoException("Titulo e mensagem devem ser diferentes");
		}
	}
}
