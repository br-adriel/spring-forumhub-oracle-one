package br.com.alura.forum_hub.domain.topico.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.curso.CursoRepository;
import br.com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;

@Component
public class CursoExiste implements ValidadorCadastroTopico {
	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public void validar(DadosCadastroTopico dados) {
		var curso = cursoRepository.getReferenceById(dados.curso());
		if (curso == null)
			throw new ValidacaoException("Curso inválido");
	}

}
