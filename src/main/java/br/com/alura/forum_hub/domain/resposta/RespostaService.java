package br.com.alura.forum_hub.domain.resposta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum_hub.domain.resposta.dto.DadosCadastroResposta;
import br.com.alura.forum_hub.domain.resposta.validation.ValidadorCadastroResposta;
import br.com.alura.forum_hub.domain.topico.TopicoRepository;
import br.com.alura.forum_hub.infra.security.auth.AuthService;

@Service
public class RespostaService {
	@Autowired
	private RespostaRepository respostaRepository;

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private List<ValidadorCadastroResposta> validadoresCadastroResposta;

	@Transactional
	public Resposta cadastrar(DadosCadastroResposta dados) {
		validadoresCadastroResposta.forEach(v -> v.validar(dados));

		var topico = topicoRepository.getReferenceById(dados.topico());
		var autor = AuthService.requestUser();

		var resposta = new Resposta(dados, topico, autor);
		respostaRepository.save(resposta);
		return resposta;
	}

	public Resposta detalhar(Long id) {
		return respostaRepository.getReferenceById(id);
	}

	public Page<Resposta> listar(Pageable paginacao) {
		return respostaRepository.findAll(paginacao);
	}
}
