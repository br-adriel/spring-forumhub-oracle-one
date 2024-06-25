package br.com.alura.forum_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum_hub.domain.usuario.UsuarioRepository;
import br.com.alura.forum_hub.domain.usuario.UsuarioService;
import br.com.alura.forum_hub.domain.usuario.dto.DadosAtualizacaoUsuario;
import br.com.alura.forum_hub.domain.usuario.dto.DadosDetalhamentoUsuario;
import br.com.alura.forum_hub.domain.usuario.dto.DadosListagemUsuario;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemUsuario>> listar(Pageable paginacao) {
		var page = repository.findAll(paginacao).map(DadosListagemUsuario::new);
		return ResponseEntity.ok().body(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id) {
		var usuario = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoUsuario> atualizar(
			@PathVariable Long id,
			@RequestBody @Valid DadosAtualizacaoUsuario dados) {
		var usuario = service.atualizar(id, dados);
		return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
