package br.com.alura.forum_hub.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroUsuario(
		@NotBlank String nome,
		@NotBlank @Email String email,
		@NotBlank @Size(min = 8, max = 32) String senha) {
}
