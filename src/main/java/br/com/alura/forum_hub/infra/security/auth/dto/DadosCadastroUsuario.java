package br.com.alura.forum_hub.infra.security.auth.dto;

import br.com.alura.forum_hub.validation.email.DtoCampoEmail;
import br.com.alura.forum_hub.validation.email.EmailNaoUtilizado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@EmailNaoUtilizado
public record DadosCadastroUsuario(
		@NotBlank String nome,
		@NotBlank @Email String email,
		@NotBlank @Size(min = 8, max = 32) String senha) implements DtoCampoEmail {
}
