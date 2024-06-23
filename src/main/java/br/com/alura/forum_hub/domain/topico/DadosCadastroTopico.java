package br.com.alura.forum_hub.domain.topico;

import br.com.alura.forum_hub.validation.TituloMensagemDiferentes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@TituloMensagemDiferentes
public record DadosCadastroTopico(
		@NotBlank String titulo,
		@NotBlank String mensagem,
		@NotNull Long autor,
		@NotNull Long curso) implements DadosTopico {
}
