package br.com.alura.forum_hub.domain.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum_hub.domain.curso.dto.DadosAtualizacaoCurso;
import br.com.alura.forum_hub.domain.curso.dto.DadosCadastroCurso;

@Service
public class CursoService {
	@Autowired
	private CursoRepository repository;

	public Page<Curso> listar(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

	public Curso detalhar(Long id) {
		return repository.getReferenceById(id);
	}

	@Transactional
	public Curso cadastrar(DadosCadastroCurso dados) {
		var curso = new Curso(dados);
		repository.save(curso);
		return curso;
	}

	@Transactional
	public Curso atualizar(Long id, DadosAtualizacaoCurso dados) {
		var curso = repository.getReferenceById(id);
		curso.atualizar(dados);
		return curso;
	}

	@Transactional
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
