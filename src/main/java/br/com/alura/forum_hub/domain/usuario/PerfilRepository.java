package br.com.alura.forum_hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	Perfil findByNome(String nome);
}
