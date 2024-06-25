package br.com.alura.forum_hub.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum_hub.domain.curso.CursoRepository;
import br.com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;
import br.com.alura.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import br.com.alura.forum_hub.domain.topico.validation.ValidadorCadastroTopico;
import br.com.alura.forum_hub.domain.usuario.Usuario;

@Service
public class TopicoService {
	@Autowired
	private List<ValidadorCadastroTopico> validadoresCadastro;

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Transactional
	public DadosDetalhamentoTopico cadastrar(DadosCadastroTopico dados) {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new BadCredentialsException("Usuário ausente ou inválido");
		}

		validadoresCadastro.forEach(v -> v.validar(dados));

		var curso = cursoRepository.getReferenceById(dados.curso());
		var autor = (Usuario) authentication.getPrincipal();
		var topico = new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(),
				StatusToptico.NAO_RESPONDIDO, autor,
				curso);
		topicoRepository.save(topico);
		return new DadosDetalhamentoTopico(topico);
	}
}
