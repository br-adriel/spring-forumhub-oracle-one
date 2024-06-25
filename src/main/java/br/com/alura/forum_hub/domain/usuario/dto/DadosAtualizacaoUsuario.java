package br.com.alura.forum_hub.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record DadosAtualizacaoUsuario(
		String nome,
		@Email String email,
		@Size(min = 8, max = 32) String senhaAtual,
		@Size(min = 8, max = 32) String senha,
		@Size(min = 8, max = 32) String confirmacaoSenha) {
}
