package br.com.alura.forum_hub.domain.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum_hub.domain.usuario.dto.DadosAtualizacaoUsuario;
import br.com.alura.forum_hub.domain.usuario.validation.ValidadorAtualizacaoUsuario;
import br.com.alura.forum_hub.infra.security.auth.AuthService;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private List<ValidadorAtualizacaoUsuario> validadoresAtualizacaoUsuario;

	public Page<Usuario> listar(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

	public Usuario detalhar(Long id) {
		return repository.getReferenceById(id);
	}

	@Transactional
	public Usuario atualizar(Long id, DadosAtualizacaoUsuario dados) {
		var usuario = repository.getReferenceById(id);
		AuthService.throwAccessDeniedIfNotRequestUser(
				usuario,
				"Você não possui permissões para alterar esse usuário");

		validadoresAtualizacaoUsuario.forEach(v -> v.validar(dados));

		String senhaCriptografada = null;
		if (dados.senha() != null) {
			senhaCriptografada = passwordEncoder.encode(dados.senha());
		}
		usuario.atualizar(dados, senhaCriptografada);
		return usuario;
	}

	public void excluir(Long id) {
		var usuario = repository.getReferenceById(id);
		AuthService.throwAccessDeniedIfNotRequestUser(usuario);
		repository.deleteById(id);
	}
}
