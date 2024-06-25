package br.com.alura.forum_hub.domain.topico.dto;

public record DadosAtualizacaoTopico(
		String titulo,
		String mensagem,
		Long curso) {
}
