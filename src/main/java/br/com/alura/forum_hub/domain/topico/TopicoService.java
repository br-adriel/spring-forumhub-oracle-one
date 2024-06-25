package br.com.alura.forum_hub.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import jakarta.persistence.EntityNotFoundException;

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

	public Page<Topico> listar(Pageable paginacao, String nomeCurso, Integer ano) {
		if (nomeCurso != null && ano != null) {
			LocalDateTime comecoAno = LocalDateTime.of(ano, 1, 1, 0, 0);
			LocalDateTime fimAno = LocalDateTime.of(ano, 12, 31, 23, 59, 59);
			return topicoRepository.findAllByCursoNomeAndDataCriacaoBetweenOrderByDataCriacaoAsc(nomeCurso, comecoAno,
					fimAno, paginacao);
		} else if (nomeCurso != null) {
			return topicoRepository.findAllByCursoNomeOrderByDataCriacaoAsc(nomeCurso,
					paginacao);
		} else if (ano != null) {
			LocalDateTime comecoAno = LocalDateTime.of(ano, 1, 1, 0, 0);
			LocalDateTime fimAno = LocalDateTime.of(ano, 12, 31, 23, 59, 59);
			return topicoRepository.findAllByDataCriacaoBetweenOrderByDataCriacaoAsc(comecoAno, fimAno, paginacao);
		}
		return topicoRepository.findAllByOrderByDataCriacaoAsc(paginacao);
	}

	public Topico detalhar(Long id) {
		return topicoRepository.getReferenceById(id);
	}

	public void excluir(Long id) {
		var topico = topicoRepository.findById(id);
		if (topico.isEmpty()) {
			throw new EntityNotFoundException();
		}
		topicoRepository.deleteById(id);
	}
}
