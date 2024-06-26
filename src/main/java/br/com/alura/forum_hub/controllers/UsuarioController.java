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

import br.com.alura.forum_hub.domain.usuario.UsuarioService;
import br.com.alura.forum_hub.domain.usuario.dto.DadosAtualizacaoUsuario;
import br.com.alura.forum_hub.domain.usuario.dto.DadosDetalhamentoUsuario;
import br.com.alura.forum_hub.domain.usuario.dto.DadosListagemUsuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuário")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<Page<DadosListagemUsuario>> listar(Pageable paginacao) {
		var page = service.listar(paginacao).map(DadosListagemUsuario::new);
		return ResponseEntity.ok().body(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id) {
		var usuario = service.detalhar(id);
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
