package br.com.alura.forum_hub.domain.usuario.validation;

import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.usuario.dto.DadosAtualizacaoUsuario;

@Component
public class SenhaIgualConfirmacaoSenha implements ValidadorAtualizacaoUsuario {
	public void validar(String senha, String confirmacaoSenha) {
		if (!senha.equals(confirmacaoSenha)) {
			throw new ValidacaoException("As senhas não são iguais");
		}
	}

	@Override
	public void validar(DadosAtualizacaoUsuario dados) {
		if (dados.senha() != null && dados.confirmacaoSenha() != null) {
			validar(dados.senha(), dados.confirmacaoSenha());
		}
	}
}
