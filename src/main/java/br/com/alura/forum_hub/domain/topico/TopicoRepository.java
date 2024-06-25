package br.com.alura.forum_hub.domain.topico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	Page<Topico> findAllByOrderByDataCriacaoAsc(Pageable paginacao);

	Page<Topico> findAllByCursoNomeAndDataCriacaoBetweenOrderByDataCriacaoAsc(
			String nomeCurso,
			LocalDateTime startDate,
			LocalDateTime endDate,
			Pageable paginacao);

	Page<Topico> findAllByCursoNomeOrderByDataCriacaoAsc(
			String nomeCurso,
			Pageable paginacao);

	Page<Topico> findAllByDataCriacaoBetweenOrderByDataCriacaoAsc(
			LocalDateTime startDate,
			LocalDateTime endDate,
			Pageable paginacao);
}
