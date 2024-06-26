package br.com.alura.forum_hub.infra.security.auth;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum_hub.domain.usuario.Perfil;
import br.com.alura.forum_hub.domain.usuario.PerfilRepository;
import br.com.alura.forum_hub.domain.usuario.Usuario;
import br.com.alura.forum_hub.domain.usuario.UsuarioRepository;
import br.com.alura.forum_hub.domain.usuario.dto.DadosCadastroUsuario;
import br.com.alura.forum_hub.domain.usuario.validation.ValidadorCadastroUsuario;
import br.com.alura.forum_hub.infra.security.auth.dto.DadosAutenticacaoBemSucedida;
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

	@Autowired
	private List<ValidadorCadastroUsuario> validadoresCadastro;

	public DadosAutenticacaoBemSucedida login(DadosLogin dados) {
		return autenticar(dados.email(), dados.senha());
	}

	@Transactional
	public DadosAutenticacaoBemSucedida registrar(DadosCadastroUsuario dados) {
		validadoresCadastro.forEach(v -> v.validar(dados));

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

	public static Usuario requestUser() {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new BadCredentialsException("Usuário ausente ou inválido");
		}
		return (Usuario) authentication.getPrincipal();
	}

	public static void throwAccessDeniedIfNotRequestUser(Usuario usuario, String msg) {
		if (!requestUser().equals(usuario)) {
			throw new AccessDeniedException(msg);
		}
	}

	public static void throwAccessDeniedIfNotRequestUser(Usuario usuario) {
		throwAccessDeniedIfNotRequestUser(usuario, "Acesso negado");
	}
}
