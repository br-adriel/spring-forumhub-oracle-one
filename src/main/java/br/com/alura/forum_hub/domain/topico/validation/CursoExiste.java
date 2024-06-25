package br.com.alura.forum_hub.domain.topico.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.curso.CursoRepository;
import br.com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import br.com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;

@Component
public class CursoExiste implements ValidadorCadastroTopico, ValidadorAtualizacaoTopico {
	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public void validar(DadosCadastroTopico dados) {
		validar(dados.curso());
	}

	@Override
	public void validar(DadosAtualizacaoTopico dados) {
		if (dados.curso() != null) {
			validar(dados.curso());
		}
	}

	private void validar(Long id) {
		cursoRepository
				.findById(id)
				.orElseThrow(() -> new ValidacaoException("Curso inválido"));
	}
}
