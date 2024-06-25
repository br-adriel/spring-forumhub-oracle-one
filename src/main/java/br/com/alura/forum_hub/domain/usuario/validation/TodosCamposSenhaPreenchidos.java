package br.com.alura.forum_hub.domain.usuario.validation;

import org.springframework.stereotype.Component;

import br.com.alura.forum_hub.domain.ValidacaoException;
import br.com.alura.forum_hub.domain.usuario.dto.DadosAtualizacaoUsuario;

@Component
public class TodosCamposSenhaPreenchidos implements ValidadorAtualizacaoUsuario {
	@Override
	public void validar(DadosAtualizacaoUsuario dados) {
		if (dados.senha() != null || dados.senhaAtual() != null || dados.confirmacaoSenha() != null) {
			if (dados.senha() == null || dados.senhaAtual() == null || dados.confirmacaoSenha() == null) {
				throw new ValidacaoException("Todos os campos de senha devem ser preenchidos para alterar o seu valor");
			}
		}
	}
}
