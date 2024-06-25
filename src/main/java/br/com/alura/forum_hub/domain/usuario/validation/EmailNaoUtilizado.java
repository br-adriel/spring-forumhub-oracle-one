package br.com.alura.forum_hub.domain.usuario.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.usuario.UsuarioRepository;
import br.com.alura.forum_hub.domain.usuario.dto.DadosCadastroUsuario;

@Component
public class EmailNaoUtilizado implements ValidadorCadastroUsuario {
	@Autowired
	private UsuarioRepository repository;

	@Override
	public void validar(DadosCadastroUsuario dados) {
		if (dados == null || dados.email() == null) {
			return;
		}

		if (repository.existsByEmail(dados.email())) {
			throw new ValidacaoException("Esse email já está em uso");
		}
	}
}
