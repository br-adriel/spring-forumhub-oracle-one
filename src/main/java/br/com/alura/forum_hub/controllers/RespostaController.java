package br.com.alura.forum_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum_hub.domain.resposta.RespostaService;
import br.com.alura.forum_hub.domain.resposta.dto.DadosCadastroResposta;
import br.com.alura.forum_hub.domain.resposta.dto.DadosDetalhamentoResposta;
import jakarta.validation.Valid;

@RestController
@RequestMapping("respostas")
public class RespostaController {
	@Autowired
	private RespostaService respostaService;

	@PostMapping
	public ResponseEntity<DadosDetalhamentoResposta> cadastrar(
			@RequestBody @Valid DadosCadastroResposta dados,
			UriComponentsBuilder uriBuilder) {
		var resposta = respostaService.cadastrar(dados);
		var uri = uriBuilder.path("/respostas/{id}")
				.buildAndExpand(resposta.getId())
				.toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
	}
}
