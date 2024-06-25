package br.com.alura.forum_hub.domain.topico.dto;

import java.time.LocalDateTime;

import br.com.alura.forum_hub.domain.topico.StatusToptico;
import br.com.alura.forum_hub.domain.topico.Topico;
import br.com.alura.forum_hub.domain.usuario.dto.DadosDetalhamentoUsuario;

public record DadosDetalhamentoTopico(
		Long id,
		String titulo,
		String mensagem,
		LocalDateTime dataCriacao,
		StatusToptico status,
		String curso,
		DadosDetalhamentoUsuario autor) {

	public DadosDetalhamentoTopico(Topico topico) {
		this(
				topico.getId(),
				topico.getTitulo(),
				topico.getMensagem(),
				topico.getDataCriacao(),
				topico.getStatus(),
				topico.getCurso().getNome(),
				new DadosDetalhamentoUsuario(topico.getAutor()));
	}

}
