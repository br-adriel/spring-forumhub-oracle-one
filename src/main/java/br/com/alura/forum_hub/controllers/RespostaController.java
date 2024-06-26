package br.com.alura.forum_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum_hub.domain.resposta.RespostaService;
import br.com.alura.forum_hub.domain.resposta.dto.DadosCadastroResposta;
import br.com.alura.forum_hub.domain.resposta.dto.DadosDetalhamentoResposta;
import br.com.alura.forum_hub.domain.resposta.dto.DadosListagemResposta;
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

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoResposta> detalhar(@PathVariable Long id) {
		var resposta = respostaService.detalhar(id);
		return ResponseEntity.ok(new DadosDetalhamentoResposta(resposta));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemResposta>> listar(Pageable paginacao) {
		var page = respostaService.listar(paginacao).map(DadosListagemResposta::new);
		return ResponseEntity.ok(page);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.respostaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
