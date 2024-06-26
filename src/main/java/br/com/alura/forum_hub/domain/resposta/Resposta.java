package br.com.alura.forum_hub.domain.resposta;

import java.time.LocalDateTime;

import br.com.alura.forum_hub.domain.topico.Topico;
import br.com.alura.forum_hub.domain.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Resposta")
@Table(name = "tb_resposta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Resposta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mensagem;
	private Boolean solucao;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	@ManyToOne(fetch = FetchType.LAZY)
	private Topico topico;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario autor;
}
