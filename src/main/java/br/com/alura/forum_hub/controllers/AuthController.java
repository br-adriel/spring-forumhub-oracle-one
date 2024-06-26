package br.com.alura.forum_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum_hub.domain.usuario.dto.DadosCadastroUsuario;
import br.com.alura.forum_hub.infra.security.auth.AuthService;
import br.com.alura.forum_hub.infra.security.auth.dto.DadosAutenticacaoBemSucedida;
import br.com.alura.forum_hub.infra.security.auth.dto.DadosLogin;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("login")
	public ResponseEntity<DadosAutenticacaoBemSucedida> login(@RequestBody @Valid DadosLogin dados) {
		return ResponseEntity.ok(authService.login(dados));
	}

	@PostMapping("register")
	@Transactional
	public ResponseEntity<DadosAutenticacaoBemSucedida> register(@RequestBody @Valid DadosCadastroUsuario dados) {
		return ResponseEntity.ok(authService.registrar(dados));
	}
}
