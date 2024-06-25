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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum_hub.domain.curso.CursoService;
import br.com.alura.forum_hub.domain.curso.dto.DadosAtualizacaoCurso;
import br.com.alura.forum_hub.domain.curso.dto.DadosCadastroCurso;
import br.com.alura.forum_hub.domain.curso.dto.DadosDetalhamentoCurso;
import br.com.alura.forum_hub.domain.curso.dto.DadosListagemCurso;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cursos")
public class CursoController {
	@Autowired
	private CursoService service;

	@GetMapping
	public ResponseEntity<Page<DadosListagemCurso>> listar(Pageable paginacao) {
		var page = service.listar(paginacao).map(DadosListagemCurso::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCurso> detalhar(@PathVariable Long id) {
		var curso = service.detalhar(id);
		return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
	}

	@PostMapping
	public ResponseEntity<DadosDetalhamentoCurso> cadastrar(
			@RequestBody @Valid DadosCadastroCurso dados,
			UriComponentsBuilder uriBuilder) {
		var curso = service.cadastrar(dados);

		var uri = uriBuilder.path("/cursos/{id}")
				.buildAndExpand(curso.getId())
				.toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCurso> atualizar(
			@PathVariable Long id,
			@RequestBody @Valid DadosAtualizacaoCurso dados) {
		var curso = service.atualizar(id, dados);
		return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
