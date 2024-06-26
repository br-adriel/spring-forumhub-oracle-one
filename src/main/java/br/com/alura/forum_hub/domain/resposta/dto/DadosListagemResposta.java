package br.com.alura.forum_hub.domain.resposta.dto;

import java.time.LocalDateTime;

import br.com.alura.forum_hub.domain.resposta.Resposta;
import br.com.alura.forum_hub.domain.topico.dto.DadosListagemTopico;
import br.com.alura.forum_hub.domain.usuario.dto.DadosListagemUsuario;

public record DadosListagemResposta(Long id,
		String mensagem,
		Boolean solucao,
		LocalDateTime dataCriacao,
		DadosListagemTopico topico,
		DadosListagemUsuario autor) {
	public DadosListagemResposta(Resposta r) {
		this(
				r.getId(),
				r.getMensagem(),
				r.getSolucao(),
				r.getDataCriacao(),
				new DadosListagemTopico(r.getTopico()),
				new DadosListagemUsuario(r.getAutor()));
	}

}
