package br.com.alura.forum_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum_hub.domain.topico.TopicoService;
import br.com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import br.com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;
import br.com.alura.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import br.com.alura.forum_hub.domain.topico.dto.DadosListagemTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópico")
public class TopicoController {
	@Autowired
	private TopicoService topicoService;

	@PostMapping
	public ResponseEntity<DadosDetalhamentoTopico> cadastrar(
			@RequestBody @Valid DadosCadastroTopico dados,
			UriComponentsBuilder uriBuilder) {
		var dadosResposta = topicoService.cadastrar(dados);
		var uri = uriBuilder
				.path("/cursos/{id}")
				.buildAndExpand(dadosResposta.id())
				.toUri();
		return ResponseEntity.created(uri).body(dadosResposta);
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemTopico>> listar(
			@RequestParam(required = false) String nomeCurso,
			@RequestParam(required = false) Integer ano,
			Pageable paginacao) {
		var page = topicoService.listar(paginacao, nomeCurso, ano).map(DadosListagemTopico::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
		var topico = topicoService.detalhar(id);
		return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		topicoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoTopico> atualizar(
			@PathVariable Long id,
			@RequestBody @Valid DadosAtualizacaoTopico dados) {
		var topico = topicoService.atualizar(id, dados);
		return ResponseEntity.ok().body(new DadosDetalhamentoTopico(topico));
	}
}
