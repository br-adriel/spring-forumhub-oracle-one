package br.com.alura.forum_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum_hub.domain.usuario.Usuario;
import br.com.alura.forum_hub.infra.security.auth.TokenService;
import br.com.alura.forum_hub.infra.security.auth.dto.DadosLogin;
import br.com.alura.forum_hub.infra.security.auth.dto.DadosLoginBemSucedido;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("login")
	public ResponseEntity<DadosLoginBemSucedido> login(@RequestBody @Valid DadosLogin dados) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
		var authentication = manager.authenticate(authenticationToken);
		var accessToken = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		return ResponseEntity.ok(new DadosLoginBemSucedido(accessToken));
	}
}
