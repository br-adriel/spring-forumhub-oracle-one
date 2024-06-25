package br.com.alura.forum_hub.domain.usuario.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.usuario.dto.DadosAtualizacaoUsuario;
import br.com.alura.forum_hub.infra.security.auth.AuthService;

@Component
public class SenhaAntigaCorreta implements ValidadorAtualizacaoUsuario {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void validar(DadosAtualizacaoUsuario dados) {
		if (dados.senhaAtual() != null) {
			validar(dados.senhaAtual());
		}
	}

	public void validar(String senhaAtual) {
		var usuario = AuthService.requestUser();
		if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
			throw new ValidacaoException("Senha atual incorreta");
		}
	}
}
