package br.com.alura.forum_hub.domain.topico.dto;

import java.time.LocalDateTime;

import br.com.alura.forum_hub.domain.topico.StatusToptico;
import br.com.alura.forum_hub.domain.topico.Topico;
import br.com.alura.forum_hub.domain.usuario.dto.DadosListagemUsuario;

public record DadosListagemTopico(
		Long id,
		String titulo,
		String mensagem,
		LocalDateTime dataCriacao,
		StatusToptico status,
		String curso,
		DadosListagemUsuario autor) {

	public DadosListagemTopico(Topico t) {
		this(
				t.getId(),
				t.getTitulo(),
				t.getMensagem(),
				t.getDataCriacao(),
				t.getStatus(),
				t.getCurso().getNome(),
				new DadosListagemUsuario(t.getAutor()));
	}

}
