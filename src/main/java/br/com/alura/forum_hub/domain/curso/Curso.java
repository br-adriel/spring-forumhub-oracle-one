package br.com.alura.forum_hub.domain.curso;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Curso")
@Table(name = "tb_curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Enumerated(EnumType.STRING)
	private CategoriaCurso categoria;

	public Curso(DadosCadastroCurso dados) {
		this.nome = dados.nome();
		this.categoria = dados.categoria();
	}

	public void atualizar(DadosAtualizacaoCurso dados) {
		if (dados.categoria() != null) {
			categoria = dados.categoria();
		}
		if (dados.nome() != null) {
			nome = dados.nome();
		}
	}
}
