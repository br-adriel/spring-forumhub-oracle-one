package br.com.alura.forum_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum_hub.domain.curso.CursoRepository;
import br.com.alura.forum_hub.domain.curso.DadosDetalhamentoCurso;
import br.com.alura.forum_hub.domain.curso.DadosListagemCurso;

@RestController
@RequestMapping("cursos")
public class CursoController {
	@Autowired
	private CursoRepository repository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemCurso>> listar(Pageable paginacao) {
		var page = repository.findAll(paginacao).map(DadosListagemCurso::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCurso> detalhar(@PathVariable Long id) {
		var curso = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
	}
}
