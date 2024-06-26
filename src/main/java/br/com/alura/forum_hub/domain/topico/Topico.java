package br.com.alura.forum_hub.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

import br.com.alura.forum_hub.domain.curso.Curso;
import br.com.alura.forum_hub.domain.resposta.Resposta;
import br.com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import br.com.alura.forum_hub.domain.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Topico")
@Table(name = "tb_topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	private String mensagem;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	@Enumerated(EnumType.STRING)
	private StatusToptico status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "autor", nullable = false)
	private Usuario autor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso", nullable = false)
	private Curso curso;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Resposta> respostas;

	public void atualizar(DadosAtualizacaoTopico dados, Curso curso) {
		if (dados.mensagem() != null) {
			this.mensagem = dados.mensagem();
		}

		if (curso != null) {
			this.curso = curso;
		}

		if (dados.titulo() != null) {
			this.titulo = dados.titulo();
		}
	}

	public void marcarComoSolucionado() {
		this.status = StatusToptico.RESPONDIDO;
	}
}
