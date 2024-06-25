package br.com.alura.forum_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum_hub.domain.topico.TopicoService;
import br.com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;
import br.com.alura.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import br.com.alura.forum_hub.domain.topico.dto.DadosListagemTopico;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
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
}
