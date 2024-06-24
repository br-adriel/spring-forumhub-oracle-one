package br.com.alura.forum_hub.infra.security.auth;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum_hub.domain.usuario.Perfil;
import br.com.alura.forum_hub.domain.usuario.PerfilRepository;
import br.com.alura.forum_hub.domain.usuario.Usuario;
import br.com.alura.forum_hub.domain.usuario.UsuarioRepository;
import br.com.alura.forum_hub.infra.security.auth.dto.DadosAutenticacaoBemSucedida;
import br.com.alura.forum_hub.infra.security.auth.dto.DadosCadastroUsuario;
import br.com.alura.forum_hub.infra.security.auth.dto.DadosLogin;

@Service
public class AuthService {
	@Autowired
	private TokenService tokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private PerfilRepository perfilRepository;

	public DadosAutenticacaoBemSucedida login(DadosLogin dados) {
		return autenticar(dados.email(), dados.senha());
	}

	@Transactional
	public DadosAutenticacaoBemSucedida registrar(DadosCadastroUsuario dados) {
		var senhaCriptografada = passwordEncoder.encode(dados.senha());
		var perfilPadrao = perfilRepository.findByNome("Estudante");
		var perfis = new HashSet<Perfil>();

		perfis.add(perfilPadrao);

		var usuario = new Usuario(dados, senhaCriptografada, perfis);
		usuarioRepository.save(usuario);

		return autenticar(usuario.getEmail(), dados.senha());
	}

	private DadosAutenticacaoBemSucedida autenticar(String email, String senha) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(email, senha);
		var authentication = manager.authenticate(authenticationToken);
		var accessToken = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		return new DadosAutenticacaoBemSucedida(accessToken);
	}
}
