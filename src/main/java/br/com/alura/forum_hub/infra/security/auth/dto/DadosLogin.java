package br.com.alura.forum_hub.infra.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
		@NotBlank @Email String email,
		@NotBlank String senha) {
}
