package br.com.alura.forum_hub.domain.resposta.dto;

import java.time.LocalDateTime;

import br.com.alura.forum_hub.domain.resposta.Resposta;
import br.com.alura.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import br.com.alura.forum_hub.domain.usuario.dto.DadosDetalhamentoUsuario;

public record DadosDetalhamentoResposta(
		Long id,
		String mensagem,
		Boolean solucao,
		LocalDateTime dataCriacao,
		DadosDetalhamentoTopico topico,
		DadosDetalhamentoUsuario autor) {
	public DadosDetalhamentoResposta(Resposta r) {
		this(
				r.getId(),
				r.getMensagem(),
				r.getSolucao(),
				r.getDataCriacao(),
				new DadosDetalhamentoTopico(r.getTopico()),
				new DadosDetalhamentoUsuario(r.getAutor()));
	}
}
